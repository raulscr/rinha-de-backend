package com.rinha.backend.KotlinRinhaBackend.controller

import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import com.rinha.backend.KotlinRinhaBackend.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("")
class PersonController(private val service: PersonService) {

    @PostMapping("pessoas")
    @ResponseStatus(HttpStatus.CREATED)
    fun insertPerson(@RequestBody person: PersonModel) {
        service.insertPerson(person)
    }

    @GetMapping("pessoas/{id}")
    fun searchPersonById(@PathVariable id: String): ResponseEntity<PersonModel> {
        val result = service.getPersonById(id.toLong()) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(result);
    }

    @GetMapping("pessoas/")
    fun searchPersonByFilter(@RequestParam("t") filter: String?): Collection<PersonModel> {
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
