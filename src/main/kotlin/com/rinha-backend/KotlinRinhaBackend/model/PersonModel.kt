package com.rinha.backend.KotlinRinhaBackend

data class PersonModel (
    val id: String
    val name: String
    val nickname: String
    val bornDate: String
    val stack: Collection<String>
)

