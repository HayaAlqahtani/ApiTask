package com.joincoded.apis.model

data class ApiPet(
    val id: Int,
    val name: String,
    val adopted: Boolean,
    val image: String,
    val age: Int,
    val gender: String,
)
