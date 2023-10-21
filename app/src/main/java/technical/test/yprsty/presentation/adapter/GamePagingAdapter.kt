package technical.test.yprsty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import technical.test.yprsty.databinding.ItemGameBinding
import technical.test.yprsty.domain.model.Game
import technical.test.yprsty.utils.extension.loadImage

class GamePagingAdapter(private val itemClicked: (Int) -> Unit) :
    PagingDataAdapter<Game, GamePagingAdapter.GamePagingViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamePagingViewHolder =
        GamePagingViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClicked
        )

    override fun onBindViewHolder(holder: GamePagingViewHolder, position: Int) {
        val game = getItem(position)
        game?.let { holder.bind(it) }
    }

    class GamePagingViewHolder(
        private val binding: ItemGameBinding,
        private val itemClicked: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.apply {
                tvName.text = game.name
                tvRating.text = game.rating
                ivThumbnail.loadImage(game.uriBackgroundImage)
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