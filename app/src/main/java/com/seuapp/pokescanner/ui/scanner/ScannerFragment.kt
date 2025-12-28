package com.seuapp.pokescanner.ui.scanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.seuapp.pokescanner.R
import com.seuapp.pokescanner.databinding.FragmentScannerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment para escanear cartas Pokémon.
 * 
 * Funcionalidades:
 * - Preview da câmera
 * - Botão de captura
 * - Processamento OCR
 * - Navegação para detalhes da carta
 */
@AndroidEntryPoint
class ScannerFragment : Fragment() {
    private var _binding: FragmentScannerBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: ScannerViewModel by viewModels()
    
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.initializeCamera(binding.previewView, viewLifecycleOwner)
        } else {
            Toast.makeText(context, R.string.error_camera_permission, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupObservers()
        setupClickListeners()
        checkCameraPermission()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                handleUiState(state)
            }
        }
    }

    private fun handleUiState(state: ScannerUiState) {
        binding.buttonScan.isEnabled = !state.isLoading
        binding.buttonScan.text = if (state.isLoading) {
            getString(R.string.button_scanning)
        } else {
            getString(R.string.button_scan)
        }
        
        state.error?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.clearError()
        }
        
        // Quando detecta informações da carta, mostra diálogo de confirmação (apenas uma vez)
        state.detectedCardNumber?.let { cardInfo ->
            // Limpa o estado ANTES de mostrar o diálogo para evitar que apareça duas vezes
            viewModel.clearDetectedCardNumber()
            showCardNumberDetectedDialog(cardInfo)
        }
        
        state.scannedCard?.let { card ->
            navigateToCardDetail(card)
            viewModel.clearScannedCard()
        }
    }
    
    private fun showCardNumberDetectedDialog(cardInfo: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Carta Detectada")
            .setMessage("$cardInfo detectada. Gostaria de pesquisar?")
            .setPositiveButton("Sim") { _, _ ->
                // Usa o número apenas para a URL (se disponível)
                val state = viewModel.uiState.value
                val numberForSearch = state.detectedCardNumberOnly ?: state.detectedCardNumber ?: cardInfo
                showSearchOptionsDialog(numberForSearch, state.detectedCardName)
            }
            .setNegativeButton("Não", null)
            .show()
    }
    
    private fun showSearchOptionsDialog(cardNumber: String, cardName: String?) {
        val message = if (cardName != null) {
            "Pesquisar $cardName ($cardNumber)?"
        } else {
            "Pesquisar carta $cardNumber?"
        }
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Onde pesquisar?")
            .setMessage(message)
            .setPositiveButton("Liga Pokemon") { _, _ ->
                openLigaPokemon(cardNumber)
            }
            .setNeutralButton("Outra opção (em breve)") { _, _ ->
                Toast.makeText(context, "Funcionalidade em breve", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    
    private fun openLigaPokemon(cardNumber: String) {
        try {
            // Pega o estado atual para verificar se tem nome
            val state = viewModel.uiState.value
            val cardName = state.detectedCardName?.lowercase()?.replace(" ", "+")
            
            // Monta a URL com nome + número se disponível, senão só número
            val url = if (cardName != null && cardName.isNotBlank()) {
                // Formata número (substitui / por %2F)
                val encodedNumber = cardNumber.replace("/", "%2F")
                // URL com nome + número: artazon+076%2F091
                "https://www.ligapokemon.com.br/?view=cards%2Fsearch&tipo=1&card=$cardName+$encodedNumber"
            } else {
                // Apenas número se não tiver nome
                val encodedNumber = cardNumber.replace("/", "%2F")
                "https://www.ligapokemon.com.br/?view=cards%2Fsearch&tipo=1&card=$encodedNumber"
            }
            
            Log.d("ScannerFragment", "Abrindo Liga Pokemon: $url")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao abrir Liga Pokemon: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupClickListeners() {
        binding.buttonScan.setOnClickListener {
            viewModel.scanCard()
        }
    }

    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.initializeCamera(binding.previewView, viewLifecycleOwner)
            }
            else -> {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun navigateToCardDetail(card: com.seuapp.pokescanner.domain.model.PokemonCard) {
        val bundle = Bundle().apply {
            putString("cardId", card.id)
        }
        findNavController().navigate(
            R.id.action_scanner_fragment_to_card_detail_fragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.releaseCamera()
        _binding = null
    }
}

