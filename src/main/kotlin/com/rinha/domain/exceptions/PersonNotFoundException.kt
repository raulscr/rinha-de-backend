package com.rinha.domain.exceptions

class PersonNotFoundException(
    override val message: String = "Person not found"
) : BusinessException()