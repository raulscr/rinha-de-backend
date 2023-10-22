package com.rinha.backend.KotlinRinhaBackend.model

import jakarta.annotation.Generated
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "person")
@Entity
data class PersonModel(
    @Id
    @Generated()
    @Column(name = "id")
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "nick_name")
    val nickname: String,
    @Column(name = "born_date")
    val bornDate: String,
    val stack: Collection<String>
)
