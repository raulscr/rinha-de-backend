package com.rinha.resources.dtos.entity

import com.rinha.domain.model.PersonModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "person", schema = "public")
data class PersonEntity(
    @Id val id: UUID,
    @Column(name = "nick_name", unique = true) val nickName: String,
    @Column(name = "name") val name: String,
    @Column(name = "date_of_birth") val dateOfBirth: LocalDate,
    @Column(name = "stack") val stack: List<String>? = null,
    @Column(name = "search") val search: String = name.lowercase() + " " + nickName.lowercase() + " " + stack.toString().lowercase()
) {
    fun toPersonModel(): PersonModel =
        PersonModel(
            id = id,
            nickName = nickName,
            name = name,
            dateOfBirth = dateOfBirth,
            stack = stack
        )

    companion object {
        fun PersonModel.toPersonEntity(): PersonEntity =
            PersonEntity(
                id = id,
                nickName = nickName,
                name = name,
                dateOfBirth = dateOfBirth,
                stack = stack
            )
    }
}