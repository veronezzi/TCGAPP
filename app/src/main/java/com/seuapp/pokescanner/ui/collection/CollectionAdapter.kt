package com.seuapp.pokescanner.ui.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seuapp.pokescanner.databinding.ItemCardBinding
import com.seuapp.pokescanner.domain.model.PokemonCard
import coil.load
import com.seuapp.pokescanner.R

/**
 * Adapter para exibir lista de cartas na coleção.
 */
class CollectionAdapter(
    private val onCardClick: (PokemonCard) -> Unit
) : ListAdapter<PokemonCard, CollectionAdapter.CardViewHolder>(CardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardViewHolder(binding, onCardClick)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CardViewHolder(
        private val binding: ItemCardBinding,
        private val onCardClick: (PokemonCard) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: PokemonCard) {
            binding.textCardName.text = card.name
            binding.textCardNumber.text = card.number
            card.prices?.mid?.let {
                binding.textCardPrice.text = "$${String.format("%.2f", it)}"
            }
            
            card.imageUrl?.let { url ->
                binding.imageCard.load(url) {
                    placeholder(android.R.drawable.ic_menu_gallery)
                    error(android.R.drawable.ic_menu_report_image)
                }
            }
            
            binding.root.setOnClickListener {
                onCardClick(card)
            }
        }
    }

    class CardDiffCallback : DiffUtil.ItemCallback<PokemonCard>() {
        override fun areItemsTheSame(oldItem: PokemonCard, newItem: PokemonCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PokemonCard, newItem: PokemonCard): Boolean {
            return oldItem == newItem
        }
    }
}

