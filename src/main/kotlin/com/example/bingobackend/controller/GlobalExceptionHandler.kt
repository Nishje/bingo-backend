package com.example.bingobackend.controller

import com.example.bingobackend.util.ErrorMessage
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorMessage> {
        val result = exception.bindingResult
        val responseMessage: String =
            if (result.fieldError != null && result.fieldError!!.defaultMessage != null) result.fieldError!!.defaultMessage.orEmpty()
            else "Validation error"
        return ResponseEntity((ErrorMessage(responseMessage)), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleValidationException(exception: ConstraintViolationException): ResponseEntity<ErrorMessage> {
        return ResponseEntity((ErrorMessage("No access rights")), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorMessage> {
        println(exception)
        return ResponseEntity((ErrorMessage("An unexpected error occurred")), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
