package com.example.partnerkin.di

import com.example.partnerkin.presentation.conferences.ConferencesViewModel
import com.example.partnerkin.data.ConferenceRepositoryImpl
import com.example.partnerkin.domain.ConferenceRepository
import com.example.partnerkin.domain.GetConferenceDetailsUseCase
import com.example.partnerkin.domain.GetConferencesUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf

val sharedModule: Module = module {
    singleOf(::ConferenceRepositoryImpl) bind ConferenceRepository::class

    factoryOf(::GetConferencesUseCase)
    factoryOf(::GetConferenceDetailsUseCase)

    viewModelOf(::ConferencesViewModel)
}