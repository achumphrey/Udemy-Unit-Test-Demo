package com.example.udemyunittest1.examples4to6

interface LoginHttpEndpointSync {

    /**
     * Log in using provided credentials
     * @return the aggregated result of login operation
     * @throws NetworkErrorException if login attempt failed due to network error
     */
    @Throws(NetworkErrorException::class)
    abstract fun loginSync(username: String, password: String): EndpointResult

    enum class EndpointResultStatus {
        SUCCESS,
        AUTH_ERROR,
        SERVER_ERROR,
        GENERAL_ERROR
    }

    class EndpointResult {

        var mStatus: EndpointResultStatus
        var mAuthToken: String

        constructor(status: EndpointResultStatus, authToken: String){
            mStatus = status
            mAuthToken = authToken
        }

        fun getStatus(): EndpointResultStatus {
            return mStatus
        }

        fun getAuthToken(): String {
            return mAuthToken
        }
    }
}