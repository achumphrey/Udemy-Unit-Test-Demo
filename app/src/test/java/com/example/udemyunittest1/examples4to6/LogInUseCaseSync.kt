package com.example.udemyunittest1.examples4to6


class LogInUseCaseSync {

    var mLoginHttpEndpointSync: LoginHttpEndpointSync
    var mAuthTokenCache: AuthTokenCache
    var mEventBusPoster: EventBusPoster

    constructor(
        loginHttpEndpointSync: LoginHttpEndpointSync,
        authTokenCache: AuthTokenCache,
        eventBusPoster: EventBusPoster
    ) {
        mLoginHttpEndpointSync = loginHttpEndpointSync
        mAuthTokenCache = authTokenCache
        mEventBusPoster = eventBusPoster
    }

    enum class UseCaseResult {
        SUCCESS,
        FAILURE,
        NETWORK_ERROR
    }

    fun loginSync(username: String, password: String): UseCaseResult {
        val endpointEndpointResult: LoginHttpEndpointSync.EndpointResult
        try {
            endpointEndpointResult = mLoginHttpEndpointSync.loginSync(username, password)
        } catch (e: NetworkErrorException) {
            return UseCaseResult.NETWORK_ERROR
        }


        if (isSuccessfulEndpointResult(endpointEndpointResult)) {
            mAuthTokenCache.cacheAuthToken(endpointEndpointResult.getAuthToken())
            mEventBusPoster.postEvent(LoggedInEvent())
            return UseCaseResult.SUCCESS
        } else {
            return UseCaseResult.FAILURE
        }
    }

    private fun isSuccessfulEndpointResult(endpointResult: LoginHttpEndpointSync.EndpointResult): Boolean {
        return endpointResult.getStatus() === LoginHttpEndpointSync.EndpointResultStatus.SUCCESS
    }

}