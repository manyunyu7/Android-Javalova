package com.feylabs.javalova.di

import com.feylabs.javalova.example.JavalovaExampleViewModel
import com.feylabs.javalova.ui.home.HomeViewModel
import com.feylabs.javalova.ui.list.ListDrinkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel { JavalovaExampleViewModel(get(),get()) }
    viewModel { HomeViewModel(get(),get()) }
    viewModel { ListDrinkViewModel(get(),get()) }
}