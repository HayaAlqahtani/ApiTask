package com.joincoded.apis.interfaceApi

import com.joincoded.apis.model.ApiPet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PetApiService {
    @GET("pets")
    suspend fun getAllPets(): List<ApiPet>

    @POST("pets")
    suspend fun AddPets(@Body pet: ApiPet):Response<ApiPet>


    @DELETE("pets/{petID}")
    suspend fun deletePet(@Path("petID")petID:Int): Response<Unit>
}