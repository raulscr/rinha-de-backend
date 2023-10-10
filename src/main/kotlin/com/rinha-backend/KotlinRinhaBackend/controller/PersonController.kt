package com.rinha.backend.KotlinRinhaBackend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class PersonController {
    @PostMapping("pessoas")
    fun insertPerson(): String {
        return "insertPerson"
    }

    @GetMapping("pessoas/{id}")
    fun searchPersonById(@PathVariable id: String): String {
        // @TODO: use a single function to filter by whatever it is (id, blablabla), if it's even
        // possible
        println(id)
        return "searchPersonById"
    }

    @GetMapping("pessoas/")
    fun searchPersonByFilter(@RequestParam("t") filter: String?): String {
        if (filter == null) {
            return "400 - bad request"
        }

        return "searchPersonByFilter $filter"
    }

    @GetMapping("contagem-pessoas")
    fun contagemPessoas(): String {
        return "contagemPessoas"
    }
}
