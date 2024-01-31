package com.example.bingobackend.controller

import com.example.bingobackend.util.ResponseType
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(exception: MethodArgumentNotValidException): ResponseEntity<ResponseType.Error> {
        val result = exception.bindingResult
        val responseMessage: String =
            if (result.fieldError != null && result.fieldError!!.defaultMessage != null) result.fieldError!!.defaultMessage.orEmpty()
            else "Validation error"
        return ResponseEntity((ResponseType.Error(responseMessage)), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleValidationException(exception: ConstraintViolationException): ResponseEntity<ResponseType.Error> {
        return ResponseEntity((ResponseType.Error("No access rights")), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ResponseType.Error> {
        return ResponseEntity((ResponseType.Error("An unexpected error occurred")), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
