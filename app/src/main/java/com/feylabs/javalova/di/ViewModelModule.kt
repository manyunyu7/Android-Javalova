package com.feylabs.javalova.di

import com.feylabs.javalova.example.JavalovaExampleViewModel
import com.feylabs.javalova.ui.detail.DetailCocktailViewModel
import com.feylabs.javalova.ui.home.HomeViewModel
import com.feylabs.javalova.ui.list.ListDrinkViewModel
import com.feylabs.javalova.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel { JavalovaExampleViewModel(get(),get()) }
    viewModel { HomeViewModel(get(),get()) }
    viewModel { ListDrinkViewModel(get(),get()) }
    viewModel { SearchViewModel(get(),get()) }
    viewModel { DetailCocktailViewModel(get(),get()) }
}