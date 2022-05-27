package com.vanced.store.network.service

import io.ktor.client.*

interface RepoService {



}

class RepoServiceImpl(
    private val client: HttpClient
) : RepoService {

}