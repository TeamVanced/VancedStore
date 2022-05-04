package com.vanced.store.di

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val json = Json {
    ignoreUnknownKeys = true
}

val httpModule = module {

    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {
            expectSuccess = false
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    single { provideKtorClient() }
}