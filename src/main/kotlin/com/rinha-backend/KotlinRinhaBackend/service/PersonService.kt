package com.rinha.backend.KotlinRinhaBackend.service

import com.rinha.backend.KotlinRinhaBackend.datasource.PersonDataSourceInterface
import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import org.springframework.stereotype.Service

@Service
class PersonService(private val dataSource: PersonDataSourceInterface) {
    fun getPersonById(id: Long): PersonModel? {
        return dataSource.getPersonById(id)
    }

    fun getPersonByFilter(filter: String): Collection<PersonModel> {
        return dataSource.getPersonByFilter(filter)
    }

    fun insertPerson(person: PersonModel) {
        return dataSource.insertPerson(person)
    }

    fun getCountPeople(): Int {
        return dataSource.getCountPeople()
    }
}
