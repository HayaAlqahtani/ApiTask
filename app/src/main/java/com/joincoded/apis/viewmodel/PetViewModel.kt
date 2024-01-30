package com.joincoded.apis.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joincoded.apis.interfaceApi.PetApiService
import com.joincoded.apis.model.ApiPet
import com.joincoded.apis.repo.PetRepo
import com.joincoded.apis.singelton.RetroFitHelper
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {

    private val PetApiService = RetroFitHelper.getInstance().create(PetApiService::class.java)
    private val repository = PetRepo(PetApiService)

    var Pets by mutableStateOf(listOf<ApiPet>())

    init {
        fetchPets()
    }

     fun fetchPets() {
        viewModelScope.launch {
            try {

                Pets = repository.getAllPets()

            } catch (e: Exception) {
print("error")
            }
        }
    }


    fun deletePet(petId: Int) {
        viewModelScope.launch {
            try {
                val response = PetApiService.deletePet(petId)
                if (response.isSuccessful) {

                } else {
                    // Handle failure, e.g., showing an error message
                }
            } catch (e: Exception) {
                // Handle any exceptions, e.g., network errors
            }
        }
    }

    fun addPet(pet: ApiPet) {
        viewModelScope.launch {
            try {
                val response = PetApiService.AddPets(pet)
                if (response.isSuccessful && response.body() != null) {
                    //
                } else {
                    //


                }
            } catch (e: Exception) {
                //

            }
        }


    }
}
