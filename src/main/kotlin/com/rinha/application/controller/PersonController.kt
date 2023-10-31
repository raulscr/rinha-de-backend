package com.rinha.application.controller

import com.rinha.application.dtos.requests.PersonRequest
import com.rinha.application.dtos.responses.PersonResponse
import com.rinha.application.dtos.responses.PersonResponse.Companion.toPersonResponse
import com.rinha.application.dtos.responses.PersonResponse.Companion.toPersonResponseList
import com.rinha.domain.ports.input.PersonPort
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class PersonController(
    private val personPort: PersonPort
) {
    @PostMapping("pessoas")
    @ResponseStatus(HttpStatus.CREATED)
    fun insertPerson(@RequestBody person: PersonRequest): ResponseEntity<PersonResponse> =
        ResponseEntity.ok(
            personPort.insertPerson(person.toPersonModel()).toPersonResponse()
        )

    @GetMapping("pessoas/{id}")
    fun searchPersonById(@PathVariable id: UUID): ResponseEntity<PersonResponse> =
        ResponseEntity.ok(
            personPort.getPersonById(id).toPersonResponse()
        )

    @GetMapping("pessoas/")
    fun searchPersonByFilter(@RequestParam("t") filter: String): ResponseEntity<List<PersonResponse>> =
        ResponseEntity.ok(
            personPort.getPersonByFilter(filter).toPersonResponseList()
        )

    @GetMapping("contagem-pessoas")
    fun countPeople(): ResponseEntity<Long> =
        ResponseEntity.ok(
            personPort.countPeople()
        )
}
