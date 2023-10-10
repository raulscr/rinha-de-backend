package com.rinha.backend.KotlinRinhaBackend.datasource

import com.rinha.backend.KotlinRinhaBackend.model.PersonModel

interface PersonDataSourceInterface {
    fun getPersonById(id: String): Collection<PersonModel>
}
