package com.rinha.domain.ports.output

import com.rinha.domain.model.PersonModel
import java.util.UUID

interface PersonRepositoryPort {
    fun insertPerson(person: PersonModel): PersonModel
    fun getPersonById(id: UUID): PersonModel?
    fun getPersonByFilter(filter: String): List<PersonModel>
    fun countPeople(): Long
}