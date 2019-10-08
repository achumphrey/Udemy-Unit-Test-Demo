package com.example.udemyunittest1.examples4to6

interface AuthTokenCache {

    abstract fun cacheAuthToken(authToken: String)

    abstract fun getAuthToken(): String
}