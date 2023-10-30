package com.rinha.application.interceptor

import com.rinha.application.dtos.responses.ErrorResponse
import com.rinha.domain.exceptions.BusinessException
import com.rinha.domain.exceptions.PersonNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(ex: Exception): ResponseEntity<ErrorResponse> =
        defaultErrorResponseEntity(ex, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(PersonNotFoundException::class)
    fun handlePersonNotFoundException(ex: Exception): ResponseEntity<ErrorResponse> =
        defaultErrorResponseEntity(ex, HttpStatus.NOT_FOUND)

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: Exception): ResponseEntity<ErrorResponse> =
        defaultErrorResponseEntity(ex, HttpStatus.UNPROCESSABLE_ENTITY)

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: Exception): ResponseEntity<ErrorResponse> =
        defaultErrorResponseEntity(ex, HttpStatus.UNPROCESSABLE_ENTITY)

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> =
        defaultErrorResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR)

    companion object {
        fun defaultErrorResponseEntity(ex: Exception, httpStatus: HttpStatus): ResponseEntity<ErrorResponse> =
            ResponseEntity(ErrorResponse(ex), httpStatus)
    }
}