package com.rinha.domain.service

import com.rinha.domain.exceptions.PersonNotFoundException
import com.rinha.domain.model.PersonModel
import com.rinha.domain.ports.input.PersonPort
import com.rinha.domain.ports.output.PersonRepositoryPort
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepositoryPort: PersonRepositoryPort
) : PersonPort {
    override fun insertPerson(person: PersonModel) =
        personRepositoryPort.insertPerson(person)

    override fun getPersonById(id: UUID): PersonModel =
        personRepositoryPort.getPersonById(id) ?: throw PersonNotFoundException()

    override fun getPersonByFilter(filter: String): List<PersonModel> =
        personRepositoryPort.getPersonByFilter(filter)

    override fun countPeople(): Long =
        personRepositoryPort.countPeople()

}
