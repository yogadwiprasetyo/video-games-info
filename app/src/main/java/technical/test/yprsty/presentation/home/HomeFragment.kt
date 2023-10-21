package technical.test.yprsty.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import org.koin.androidx.viewmodel.ext.android.viewModel
import technical.test.core.utils.loadImage
import technical.test.core.utils.setup
import technical.test.yprsty.R
import technical.test.yprsty.databinding.FragmentHomeBinding
import timber.log.Timber

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var gamePagingAdapter: technical.test.core.presentation.adapter.GamePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
        viewModel.loadGames()
    }

    private fun setupView() {
        gamePagingAdapter = technical.test.core.presentation.adapter.GamePagingAdapter { gameId ->
            val action = HomeFragmentDirections.actionNavigationHomeToDetailActivity(gameId)
            findNavController().navigate(action)
        }
        gamePagingAdapter.withLoadStateFooter(
            footer = technical.test.core.presentation.adapter.LoadingStateAdapter { gamePagingAdapter.retry() }
        )
        binding.rvGames.setup(gamePagingAdapter)
    }

    private fun observeState() {
        viewModel.games.observe(viewLifecycleOwner, ::onResultStateReceive)
    }

    private fun onResultStateReceive(gamesUiState: GamesUiState) {
        when (gamesUiState) {
            GamesUiState.Loading -> onLoading()
            is GamesUiState.Error -> onError(gamesUiState.exception)
            is GamesUiState.Success -> onSuccess(gamesUiState.games)
        }
    }

    private fun onLoading() {
        showViewBasedOnState(isLoading = true, isError = false, isSuccess = false)
    }

    private fun onError(exception: Throwable) {
        Timber.e(exception)
        binding.ivInfo.loadImage(R.drawable.svg_warning)
        binding.tvInfo.text = exception.message
        showViewBasedOnState(isLoading = false, isError = true, isSuccess = false)
    }

    private fun onSuccess(gamePagingData: PagingData<technical.test.core.domain.model.Game>) {
        gamePagingAdapter.submitData(lifecycle, gamePagingData)
        showViewBasedOnState(isLoading = false, isError = false, isSuccess = true)
    }

    private fun showViewBasedOnState(isLoading: Boolean, isError: Boolean, isSuccess: Boolean) {
        binding.apply {
            rvGames.isVisible = isSuccess
            ivInfo.isVisible = isError
            tvInfo.isVisible = isError
            progress.isVisible = isLoading
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}