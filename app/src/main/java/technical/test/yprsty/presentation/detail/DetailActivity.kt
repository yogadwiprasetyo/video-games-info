package technical.test.yprsty.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.navArgs
import org.koin.android.ext.android.inject
import technical.test.yprsty.R
import technical.test.yprsty.databinding.ActivityDetailBinding
import technical.test.yprsty.domain.model.Game
import technical.test.yprsty.utils.extension.loadImage
import timber.log.Timber

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by inject()

    private val thisActivity by lazy { this }
    private val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        Timber.w("Check gameId=${args.gameId}")

        observeState()
        viewModel.loadDetail(args.gameId)
    }

    private fun observeState() {
        viewModel.game.observe(thisActivity, ::onGameStateReceive)
    }

    private fun onGameStateReceive(gameState: GameUiState) {
        when (gameState) {
            GameUiState.Loading -> onLoading()
            is GameUiState.Error -> onError(gameState.throwable)
            is GameUiState.Success -> onSuccess(gameState.game)
        }
    }

    private fun onLoading() {
        showViewBasedOnState(isLoading = true, isError = false, isSuccess = false)
    }

    private fun onError(exception: Throwable) {
        Timber.e(exception)
        binding.ivInfo.loadImage(R.drawable.svg_warning)
        binding.tvInfo.text = exception.localizedMessage
        showViewBasedOnState(isLoading = false, isError = true, isSuccess = false)
    }

    private fun onSuccess(game: Game) {
        binding.apply {
            ivBackground.loadImage(game.uriBackgroundImage)
            tvName.text = game.name
            tvRating.text = game.rating
            tvPlay.text = getString(R.string.play_time_hours, game.playTime.toString())
            tvReleaseDate.text = game.releasedDate
            tvDescription.text = game.description
        }

        setupButtonFavorite(game.isFavorite)
        showViewBasedOnState(isLoading = false, isError = false, isSuccess = true)
    }

    private fun showViewBasedOnState(isLoading: Boolean, isError: Boolean, isSuccess: Boolean) {
        binding.apply {
            btnFavorite.isVisible = isSuccess
            ivBackground.isVisible = isSuccess
            tvName.isVisible = isSuccess
            icRating.isVisible = isSuccess
            tvRating.isVisible = isSuccess
            icPlay.isVisible = isSuccess
            tvPlay.isVisible = isSuccess
            titleReleaseDate.isVisible = isSuccess
            tvReleaseDate.isVisible = isSuccess
            tvDescription.isVisible = isSuccess
            progress.isVisible = isLoading
            ivInfo.isVisible = isError
            tvInfo.isVisible = isError
        }
    }

    private fun setupButtonFavorite(isFavorite: Boolean) {
        setButtonIconFavorite(isFavorite)

        binding.btnFavorite.setOnClickListener {
            viewModel.updateFavoriteState(args.gameId)
            setButtonIconFavorite(!isFavorite)
        }
    }

    private fun setButtonIconFavorite(stateFavorite: Boolean) {
        val iconFavorite = if (stateFavorite) {
            R.drawable.round_favorite_24
        } else {
            R.drawable.round_favorite_border_24
        }
        binding.btnFavorite.setImageResource(iconFavorite)
    }
}