package technical.test.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import technical.test.core.databinding.ItemGameBinding
import technical.test.core.domain.model.Game
import technical.test.core.utils.loadImage

class GameAdapter(private val itemClicked: (Int) -> Unit) :
    ListAdapter<Game, GameAdapter.GameViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder =
        GameViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClicked
        )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
        game?.let { holder.bind(it) }
    }

    class GameViewHolder(
        private val binding: ItemGameBinding,
        private val itemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.apply {
                ivThumbnail.loadImage(game.uriBackgroundImage)
                tvName.text = game.name
                tvRating.text = game.rating
            }
            itemView.setOnClickListener { itemClicked(game.id) }
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}