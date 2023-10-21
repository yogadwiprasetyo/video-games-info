package technical.test.yprsty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import technical.test.yprsty.databinding.ItemLoadingBinding

class LoadingStateAdapter(private val refresh: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder = LoadingStateViewHolder(
        ItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        refresh
    )

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadingStateViewHolder(private val binding: ItemLoadingBinding, refresh: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRefresh.setOnClickListener { refresh.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.tvError.text = loadState.error.localizedMessage
            }
            binding.apply {
                progress.isVisible = loadState is LoadState.Loading
                btnRefresh.isVisible = loadState is LoadState.Error
                tvError.isVisible = loadState is LoadState.Error
            }
        }

    }
}