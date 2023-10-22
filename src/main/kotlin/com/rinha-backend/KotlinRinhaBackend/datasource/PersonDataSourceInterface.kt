package com.rinha.backend.KotlinRinhaBackend.datasource

import com.rinha.backend.KotlinRinhaBackend.model.PersonModel

interface PersonDataSourceInterface {
    fun getPersonById(id: Long): PersonModel
    fun getPersonByFilter(filter: String): Collection<PersonModel>
    fun insertPerson(people: Collection<PersonModel>)
    fun getCountPeople(): Int
}
