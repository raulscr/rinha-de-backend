package com.rinha.backend.KotlinRinhaBackend.service

import com.rinha.backend.KotlinRinhaBackend.datasource.PersonDataSourceInterface
import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import org.springframework.stereotype.Service

@Service
class PersonService(private val dataSource: PersonDataSourceInterface) {
    fun getPersonById(id: String): Collection<PersonModel> {
        return dataSource.getPersonById(id)
    }

    fun getPersonByFilter(filter: String): Collection<PersonModel> {
        return dataSource.getPersonByFilter(filter)
    }

    fun insertPerson(people: Collection<PersonModel>) {
        return dataSource.insertPerson(people)
    }

    fun getCountPeople(): Int {
        return dataSource.getCountPeople()
    }
}
