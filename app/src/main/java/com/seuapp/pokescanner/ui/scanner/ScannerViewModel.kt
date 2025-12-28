package com.seuapp.pokescanner.ui.scanner

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuapp.pokescanner.core.camera.CameraXProvider
import com.seuapp.pokescanner.core.crop.CardCropper
import com.seuapp.pokescanner.core.image.BitmapRotator
import com.seuapp.pokescanner.core.image.ImagePreprocessor
import com.seuapp.pokescanner.core.ocr.CardInfoOcr
import com.seuapp.pokescanner.data.repository.PokemonCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * ViewModel para a tela de scanner.
 */
@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val pokemonCardRepository: PokemonCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScannerUiState())
    val uiState: StateFlow<ScannerUiState> = _uiState.asStateFlow()

    private var cameraProvider: CameraXProvider? = null
    private val cameraExecutor = Executors.newSingleThreadExecutor()
    private val cardCropper = CardCropper()
    private val imagePreprocessor = ImagePreprocessor()
    private val cardInfoOcr = CardInfoOcr()

    fun initializeCamera(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            try {
                val context = previewView.context
                val provider = CameraXProvider(context, lifecycleOwner, cameraExecutor)
                provider.initialize(previewView)
                cameraProvider = provider
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Erro ao inicializar câmera: ${e.message}"
                )
            }
        }
    }

    fun scanCard() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
                detectedCardNumber = null,
                detectedCardName = null,
                detectedCardNumberOnly = null,
                scannedCard = null
            )

            try {
                val bitmap = withContext(Dispatchers.IO) {
                    cameraProvider?.captureFrame()
                }

                if (bitmap == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Erro ao capturar imagem da câmera"
                    )
                    return@launch
                }

                // Rotaciona a imagem se necessário (CameraX pode retornar rotacionada)
                val rotatedBitmap = BitmapRotator.rotateForPortrait(bitmap)
                
                // Crop da carta (por enquanto retorna a imagem inteira)
                val cardBitmap = cardCropper.cropCard(rotatedBitmap)
                
                // Pré-processa a imagem para melhorar o OCR
                val enhancedBitmap = imagePreprocessor.enhanceForOcr(cardBitmap)
                
                // Extrai nome e número da carta
                val cardInfo = cardInfoOcr.extractCardInfo(enhancedBitmap)

                if (cardInfo == null || (cardInfo.name == null && cardInfo.number == null)) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Informações da carta não detectadas. Tente novamente."
                    )
                    return@launch
                }

                // Cria string formatada com nome e número
                val cardInfoText = buildString {
                    if (cardInfo.name != null) {
                        append(cardInfo.name)
                        if (cardInfo.number != null) {
                            append(" ")
                        }
                    }
                    if (cardInfo.number != null) {
                        append(cardInfo.number)
                    }
                }

                // Armazena informações detectadas
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = null,
                    detectedCardNumber = cardInfoText,
                    detectedCardName = cardInfo.name,
                    detectedCardNumberOnly = cardInfo.number
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Erro ao escanear carta: ${e.message}"
                )
            }
        }
    }
    
    fun clearDetectedCardNumber() {
        _uiState.value = _uiState.value.copy(detectedCardNumber = null)
    }

    fun clearScannedCard() {
        _uiState.value = _uiState.value.copy(scannedCard = null)
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun releaseCamera() {
        cameraProvider?.release()
        cardInfoOcr.release()
        cameraExecutor.shutdown()
    }
}

