package com.seuapp.pokescanner.ui.scanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
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
        
        // Quando detecta número, mostra diálogo de confirmação
        state.detectedCardNumber?.let { cardNumber ->
            showCardNumberDetectedDialog(cardNumber)
            viewModel.clearDetectedCardNumber()
        }
        
        state.scannedCard?.let { card ->
            navigateToCardDetail(card)
            viewModel.clearScannedCard()
        }
    }
    
    private fun showCardNumberDetectedDialog(cardNumber: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Carta Detectada")
            .setMessage("Carta $cardNumber detectada. Gostaria de pesquisar?")
            .setPositiveButton("Sim") { _, _ ->
                showSearchOptionsDialog(cardNumber)
            }
            .setNegativeButton("Não", null)
            .show()
    }
    
    private fun showSearchOptionsDialog(cardNumber: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Onde pesquisar?")
            .setItems(arrayOf("Liga Pokemon", "Outra opção (em breve)")) { _, which ->
                when (which) {
                    0 -> openLigaPokemon(cardNumber)
                    1 -> {
                        // Não faz nada por enquanto
                        Toast.makeText(context, "Funcionalidade em breve", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    
    private fun openLigaPokemon(cardNumber: String) {
        try {
            // Formata o número para a URL (substitui / por %2F)
            val encodedNumber = cardNumber.replace("/", "%2F")
            val url = "https://www.ligapokemon.com.br/?view=cards%2Fsearch&tipo=1&card=$encodedNumber"
            
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

