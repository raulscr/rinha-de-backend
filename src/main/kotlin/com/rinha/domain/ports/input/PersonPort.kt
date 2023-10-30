package com.rinha.domain.ports.input

import com.rinha.domain.model.PersonModel
import java.util.UUID

interface PersonPort {
    fun insertPerson(person: PersonModel): PersonModel
    fun getPersonById(id: UUID): PersonModel
    fun getPersonByFilter(filter: String): List<PersonModel>
    fun countPeople(): Long
}