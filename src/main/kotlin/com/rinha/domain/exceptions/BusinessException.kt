package com.rinha.domain.exceptions

open class BusinessException(
    override val message: String = "Business exception"
) : RuntimeException()