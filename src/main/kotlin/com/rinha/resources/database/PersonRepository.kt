package com.rinha.resources.database

import com.rinha.resources.dtos.entity.PersonEntity
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<PersonEntity, UUID> {
    fun findBySearchLike(filter: String): List<PersonEntity>
}