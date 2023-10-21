package technical.test.yprsty.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import technical.test.yprsty.R
import technical.test.yprsty.databinding.FragmentSearchBinding
import technical.test.yprsty.domain.model.Game
import technical.test.yprsty.presentation.adapter.GameAdapter
import technical.test.yprsty.utils.extension.loadImage
import technical.test.yprsty.utils.extension.setup
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val disposables = CompositeDisposable()
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
        setupReactiveInput()
    }

    private fun setupView() {
        gameAdapter = GameAdapter { gameId ->
            val action = SearchFragmentDirections.actionNavigationSearchToDetailActivity(gameId)
            findNavController().navigate(action)
        }
        binding.rvGames.setup(gameAdapter)
    }

    private fun observeState() {
        viewModel.results.observe(viewLifecycleOwner, ::onResultStateReceive)
    }

    private fun setupReactiveInput() {
        val searchGamesStream = binding.etQuery.textChanges()
            .skipInitialValue()
            .debounce(1, TimeUnit.SECONDS)
            .map { it.toString() }
        val disposeSearchGames = searchGamesStream.subscribe { query ->
            Timber.i("Check query=$query")
            viewModel.searchGames(query)
        }
        disposables.add(disposeSearchGames)
    }

    private fun onResultStateReceive(resultState: SearchGamesUiState) {
        when (resultState) {
            SearchGamesUiState.Loading -> onLoading()
            SearchGamesUiState.Empty -> onEmpty()
            is SearchGamesUiState.Error -> onError(resultState.throwable)
            is SearchGamesUiState.Success -> onSuccess(resultState.results)
        }
    }

    private fun onLoading() {
        showViewBasedOnState(
            isError = false,
            isLoading = true,
            isSuccess = false,
            isEmpty = false
        )
    }

    private fun onEmpty() {
        binding.ivInfo.loadImage(R.drawable.svg_no_data)
        binding.tvInfo.text = getString(R.string.no_data)
        showViewBasedOnState(
            isError = false,
            isLoading = false,
            isSuccess = false,
            isEmpty = true
        )
    }

    private fun onError(exception: Throwable) {
        Timber.e(exception)
        binding.ivInfo.loadImage(R.drawable.svg_warning)
        binding.tvInfo.text = exception.message
        showViewBasedOnState(
            isError = true,
            isLoading = false,
            isSuccess = false,
            isEmpty = false
        )
    }

    private fun onSuccess(resultGames: List<Game>) {
        gameAdapter.submitList(null)
        gameAdapter.submitList(resultGames)
        showViewBasedOnState(
            isError = false,
            isLoading = false,
            isSuccess = true,
            isEmpty = false
        )
    }

    private fun showViewBasedOnState(
        isError: Boolean,
        isLoading: Boolean,
        isSuccess: Boolean,
        isEmpty: Boolean
    ) {
        binding.apply {
            rvGames.isVisible = isSuccess
            ivInfo.isVisible = isError || isEmpty
            tvInfo.isVisible = isError || isEmpty
            progress.isVisible = isLoading
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        disposables.dispose()
    }
}