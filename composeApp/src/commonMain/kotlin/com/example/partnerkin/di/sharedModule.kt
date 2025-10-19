package com.example.partnerkin.di

import com.example.partnerkin.data.ConferenceRepositoryImpl
import com.example.partnerkin.domain.ConferenceRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule: Module = module {
    factory<ConferenceRepository> { ConferenceRepositoryImpl() }

}