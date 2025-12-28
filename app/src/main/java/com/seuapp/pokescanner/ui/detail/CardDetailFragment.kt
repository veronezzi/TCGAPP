package com.seuapp.pokescanner.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seuapp.pokescanner.R
import com.seuapp.pokescanner.databinding.FragmentCardDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import coil.load

/**
 * Fragment para exibir detalhes da carta.
 */
@AndroidEntryPoint
class CardDetailFragment : Fragment() {
    private var _binding: FragmentCardDetailBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: CardDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val cardId = arguments?.getString("cardId") ?: ""
        viewModel.loadCard(cardId)
        
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                state.card?.let { card ->
                    displayCard(card)
                }
            }
        }
    }

    private fun displayCard(card: com.seuapp.pokescanner.domain.model.PokemonCard) {
        binding.textCardName.text = card.name
        binding.textCardNumber.text = card.number
        binding.textCardSet.text = card.set.name
        
        card.prices?.mid?.let {
            binding.textCardPrice.text = getString(
                R.string.card_price,
                String.format("%.2f", it)
            )
        }
        
        (card.imageUrlHiRes ?: card.imageUrl)?.let { url ->
            binding.imageCard.load(url) {
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }
        }
    }

    private fun setupClickListeners() {
        binding.buttonAddToCollection.setOnClickListener {
            // TODO: Implementar adicionar à coleção
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

