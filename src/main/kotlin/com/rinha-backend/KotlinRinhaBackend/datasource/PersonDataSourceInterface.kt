package com.rinha.backend.KotlinRinhaBackend.datasource

interface PersonDataSourceInterface {
    fun getPersonById(id: String): Collection<PersonModel>
}
