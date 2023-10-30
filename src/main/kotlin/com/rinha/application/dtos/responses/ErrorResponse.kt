package com.rinha.application.dtos.responses

data class ErrorResponse(
    val message: String
) {
    companion object {
        private const val DEFAULT_MESSAGE = "An unexpected error occurred"
        operator fun invoke(ex: Exception): ErrorResponse =
            ErrorResponse(ex.message ?: DEFAULT_MESSAGE)
    }
}