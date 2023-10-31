package com.rinha.domain.model

import java.time.LocalDate
import java.util.UUID

data class PersonModel(
    val id: UUID = UUID.randomUUID(),
    val nickName: String,
    val name: String,
    val dateOfBirth: LocalDate,
    val stack: List<String>? = null
)
