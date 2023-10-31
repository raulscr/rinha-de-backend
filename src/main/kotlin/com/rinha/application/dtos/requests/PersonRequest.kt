package com.rinha.application.dtos.requests

import com.rinha.domain.model.PersonModel
import java.time.LocalDate

data class PersonRequest(
    val apelido: String,
    val nome: String,
    val nascimento: LocalDate,
    val stack: List<String>? = null
) {
    fun toPersonModel(): PersonModel =
        PersonModel(
            nickName = apelido,
            name = nome,
            dateOfBirth = nascimento,
            stack = stack
        )
}
