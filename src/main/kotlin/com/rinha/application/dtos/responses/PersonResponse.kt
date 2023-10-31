package com.rinha.application.dtos.responses

import com.rinha.domain.model.PersonModel
import java.time.LocalDate
import java.util.UUID

data class PersonResponse(
    val id: UUID,
    val apelido: String,
    val nome: String,
    val nascimento: LocalDate,
    val stack: List<String>? = null
) {
    companion object {
        fun List<PersonModel>.toPersonResponseList(): List<PersonResponse> =
            map { it.toPersonResponse() }

        fun PersonModel.toPersonResponse(): PersonResponse =
            PersonResponse(
                id = id,
                apelido = nickName,
                nome = name,
                nascimento = dateOfBirth,
                stack = stack
            )
    }
}