package com.rinha.backend.KotlinRinhaBackend

interface PersonDataSourceInterface {
    fun getPersonById(id: String): Collection<PersonModel>
}
