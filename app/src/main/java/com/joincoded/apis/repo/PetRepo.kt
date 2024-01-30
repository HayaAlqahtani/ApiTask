package com.joincoded.apis.repo

import com.joincoded.apis.interfaceApi.PetApiService

class PetRepo(private val api: PetApiService) {
    suspend fun getAllPets() = api.getAllPets() }
