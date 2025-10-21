package com.example.partnerkin.di

import com.example.partnerkin.presentation.conferences.ConferencesViewModel
import com.example.partnerkin.data.ConferenceRepositoryImpl
import com.example.partnerkin.domain.ConferenceRepository
import com.example.partnerkin.domain.GetConferenceDetailsUseCase
import com.example.partnerkin.domain.GetConferencesUseCase
import com.example.partnerkin.presentation.details.ConferenceDetailsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val sharedModule: Module = module {
    singleOf(::ConferenceRepositoryImpl) bind ConferenceRepository::class

    factoryOf(::GetConferencesUseCase)
    factoryOf(::GetConferenceDetailsUseCase)

    viewModel<ConferencesViewModel> { ConferencesViewModel(get()) }
    viewModel<ConferenceDetailsViewModel> { params ->
        ConferenceDetailsViewModel(
            conferenceId = params.get(),
            getConferenceDetailsUseCase = get()
        )
    }
}