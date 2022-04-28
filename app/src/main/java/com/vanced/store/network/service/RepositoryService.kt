package com.vanced.store.network.service

import io.ktor.client.*

interface RepositoryService {



}

class RepositoryServiceImpl(
    private val client: HttpClient
) : RepositoryService {

}