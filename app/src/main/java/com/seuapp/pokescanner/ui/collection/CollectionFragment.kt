package com.seuapp.pokescanner.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.seuapp.pokescanner.R
import com.seuapp.pokescanner.databinding.FragmentCollectionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment para exibir a coleção de cartas do usuário.
 */
@AndroidEntryPoint
class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: CollectionViewModel by viewModels()
    private lateinit var adapter: CollectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CollectionAdapter { card ->
            navigateToCardDetail(card.id)
        }
        binding.recyclerViewCards.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewCards.adapter = adapter
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                adapter.submitList(state.cards)
                binding.textTotalValue.text = getString(
                    R.string.collection_total_value,
                    state.totalValue
                )
            }
        }
    }

    private fun navigateToCardDetail(cardId: String) {
        val bundle = Bundle().apply {
            putString("cardId", cardId)
        }
        findNavController().navigate(
            R.id.action_collection_fragment_to_card_detail_fragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

