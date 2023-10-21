package technical.test.yprsty.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import technical.test.core.utils.loadImage
import technical.test.core.utils.setup
import technical.test.yprsty.R
import technical.test.yprsty.databinding.FragmentFavoriteBinding
import timber.log.Timber

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<FavoriteViewModel>()

    private lateinit var gameAdapter: technical.test.core.presentation.adapter.GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFavorites()
    }

    private fun setupView() {
        gameAdapter = technical.test.core.presentation.adapter.GameAdapter { gameId ->
            val action = FavoriteFragmentDirections.actionNavigationFavoriteToDetailActivity(gameId)
            findNavController().navigate(action)
        }
        binding.rvFavoriteGames.setup(gameAdapter)
    }

    private fun observeState() {
        viewModel.gameFavorites.observe(viewLifecycleOwner, ::onFavoriteGamesStateReceive)
    }

    private fun onFavoriteGamesStateReceive(favoriteUiState: FavoriteGamesUiState) {
        when (favoriteUiState) {
            FavoriteGamesUiState.Empty -> onEmpty()
            is FavoriteGamesUiState.Error -> onError(favoriteUiState.throwable)
            is FavoriteGamesUiState.Success -> onSuccess(favoriteUiState.gameFavorites)
        }
    }

    private fun onEmpty() {
        binding.ivInfo.loadImage(R.drawable.svg_warning)
        binding.tvInfo.text = getString(R.string.empty_info)
        showViewBasedOnState(isError = false, isSuccess = false, isEmpty = true)
    }

    private fun onError(exception: Throwable) {
        Timber.e(exception)
        binding.ivInfo.loadImage(R.drawable.svg_warning)
        binding.tvInfo.text = exception.message
        showViewBasedOnState(isError = true, isSuccess = false, isEmpty = false)
    }

    private fun onSuccess(favoriteGames: List<technical.test.core.domain.model.Game>) {
        gameAdapter.submitList(null)
        gameAdapter.submitList(favoriteGames)
        showViewBasedOnState(isError = false, isSuccess = true, isEmpty = false)
    }

    private fun showViewBasedOnState(isError: Boolean, isSuccess: Boolean, isEmpty: Boolean) {
        binding.apply {
            rvFavoriteGames.isVisible = isSuccess
            ivInfo.isVisible = isError || isEmpty
            tvInfo.isVisible = isError || isEmpty
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}