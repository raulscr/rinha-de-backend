package com.rinha.backend.KotlinRinhaBackend.controller

import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.rinha.backend.KotlinRinhaBackend.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus

@RestController
@RequestMapping("")
class PersonController(private val service: PersonService) {

    @PostMapping("pessoas")
    @ResponseStatus(HttpStatus.CREATED)
    fun insertPerson(@RequestBody person: PersonModel) {
        service.insertPerson(listOf(person))
    }

    @GetMapping("pessoas/{id}")
    fun searchPersonById(@PathVariable id: String): PersonModel {
        return service.getPersonById(id)
    }

    @GetMapping("pessoas/")
    fun searchPersonByFilter(@RequestParam("t") filter: String?): Collection<PersonModel>  {
        if (filter == null) {
            return listOf()
        }

        return service.getPersonByFilter(filter)
    }

    @GetMapping("contagem-pessoas")
    fun contagemPessoas(): Int {
        return service.getCountPeople()
    }
}
