package com.example.bingobackend.util

sealed class ResponseType<out T> {
    data class SuccessMessage<out T>(val key: String, val value: T) : ResponseType<T>() {
        constructor(value: T = "success" as T) : this("message", value)
        override fun toString(): String {
            return "$key=$value"
        }
    }
    data class SuccessObject<out T>(val data: T) : ResponseType<T>() {

        override fun toString(): String {
            return data.toString()
        }
    }
    data class Error(val error: String) : ResponseType<Nothing>()
}