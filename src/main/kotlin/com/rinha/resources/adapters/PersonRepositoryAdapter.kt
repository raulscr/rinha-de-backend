package com.rinha.resources.adapters

import com.rinha.domain.model.PersonModel
import com.rinha.domain.ports.output.PersonRepositoryPort
import com.rinha.resources.database.PersonRepository
import com.rinha.resources.dtos.entity.PersonEntity.Companion.toPersonEntity
import java.util.UUID
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Component

@Component
class PersonRepositoryAdapter(private val personRepository: PersonRepository) : PersonRepositoryPort {
    override fun insertPerson(person: PersonModel): PersonModel =
        personRepository.save(person.toPersonEntity()).toPersonModel()

    override fun getPersonById(id: UUID): PersonModel? =
        personRepository.findById(id).getOrNull()?.toPersonModel()

    override fun getPersonByFilter(filter: String): List<PersonModel> =
        personRepository.findBySearchLike(filter.toLikeFormat()).map { it.toPersonModel() }

    override fun countPeople(): Long =
        personRepository.count()

    companion object {
        private const val LIKE = "%"
        fun String.toLikeFormat(): String = LIKE+this.lowercase()+LIKE
    }
}