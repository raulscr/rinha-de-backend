package com.rinha.backend.KotlinRinhaBackend.model

data class PersonModel(
    val id: Long? = null,
    val name: String,
    val nickName: String,
    val bornDate: String,
    val stack: Collection<String>? = null
)
