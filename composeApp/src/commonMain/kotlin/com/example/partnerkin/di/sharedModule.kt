package com.example.partnerkin.di

import ConferencesViewModel
import com.example.partnerkin.data.ConferenceRepositoryImpl
import com.example.partnerkin.domain.ConferenceRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf

val sharedModule: Module = module {
    factoryOf(::ConferenceRepositoryImpl) bind ConferenceRepository::class

    viewModelOf(::ConferencesViewModel)
}