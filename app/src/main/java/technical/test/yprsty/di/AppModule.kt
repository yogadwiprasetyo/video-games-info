package technical.test.yprsty.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import technical.test.yprsty.presentation.detail.DetailViewModel
import technical.test.yprsty.presentation.favorite.FavoriteViewModel
import technical.test.yprsty.presentation.home.HomeViewModel
import technical.test.yprsty.presentation.search.SearchViewModel

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}