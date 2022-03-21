package com.vanced.store.network.service

import io.ktor.client.*

interface BrowseService

class BrowseServiceImpl(
    private val client: HttpClient
) : BrowseService