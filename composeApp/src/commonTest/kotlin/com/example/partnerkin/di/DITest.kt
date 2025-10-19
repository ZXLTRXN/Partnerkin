package com.example.partnerkin.di

import org.koin.core.annotation.KoinInternalApi
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.test.Test

class DITest : KoinTest {

    @OptIn(KoinInternalApi::class)
    @Test
    fun checkAllModules() {
        checkModules {
            modules(sharedModule, networkModule)
        }
    }
}
