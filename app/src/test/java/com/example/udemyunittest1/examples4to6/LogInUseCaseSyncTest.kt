package com.example.udemyunittest1.examples4to6

import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LogInUseCaseSyncTest{

    val USERNAME = "username"
    val PASSWORD = "password"
    val AUTH_TOKEN = "authToken"

    lateinit var mLoginHttpEndpointSyncTd: LoginHttpEndpointSyncTd
    lateinit var mAuthTokenCacheTd: AuthTokenCacheTd
    lateinit var mEventBusPosterTd: EventBusPosterTd

     lateinit var SUT : LogInUseCaseSync

    @Before
    @Throws(Exception::class)
    fun setup() {
        mLoginHttpEndpointSyncTd = LoginHttpEndpointSyncTd()
        mAuthTokenCacheTd = AuthTokenCacheTd()
        mEventBusPosterTd = EventBusPosterTd()
        SUT = LogInUseCaseSync(mLoginHttpEndpointSyncTd, mAuthTokenCacheTd, mEventBusPosterTd)
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_success_usernameAndPasswordPassedToEndpoint() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat<String>(mLoginHttpEndpointSyncTd.mUsername, `is`<String>(USERNAME))
        assertThat<String>(mLoginHttpEndpointSyncTd.mPassword, `is`<String>(PASSWORD))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_success_authTokenCached() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(AUTH_TOKEN))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_generalError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mIsGeneralError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(""))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_authError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mIsAuthError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(""))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_serverError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mIsServerError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mAuthTokenCacheTd.getAuthToken(), `is`(""))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_success_loggedInEventPosted() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat<Any>(
            mEventBusPosterTd.mEvent,
            `is`<Any>(instanceOf<Any>(LoggedInEvent::class.java))
        )
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_generalError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsGeneralError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mEventBusPosterTd.mInteractionsCount, `is`(0))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_authError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsAuthError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mEventBusPosterTd.mInteractionsCount, `is`(0))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_serverError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsServerError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(mEventBusPosterTd.mInteractionsCount, `is`(0))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_success_successReturned() {
        val result = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LogInUseCaseSync.UseCaseResult.SUCCESS))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_serverError_failureReturned() {
        mLoginHttpEndpointSyncTd.mIsServerError = true
        val result = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LogInUseCaseSync.UseCaseResult.FAILURE))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_authError_failureReturned() {
        mLoginHttpEndpointSyncTd.mIsAuthError = true
        val result = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LogInUseCaseSync.UseCaseResult.FAILURE))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_generalError_failureReturned() {
        mLoginHttpEndpointSyncTd.mIsGeneralError = true
        val result = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LogInUseCaseSync.UseCaseResult.FAILURE))
    }

    @Test
    @Throws(Exception::class)
    fun loginSync_networkError_networkErrorReturned() {
        mLoginHttpEndpointSyncTd.mIsNetworkError = true
        val result = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(result, `is`(LogInUseCaseSync.UseCaseResult.NETWORK_ERROR))
    }

    // ---------------------------------------------------------------------------------------------
    // Helper classes

    class LoginHttpEndpointSyncTd : LoginHttpEndpointSync {
        var mUsername = ""
        var mPassword = ""
        var mIsGeneralError: Boolean = false
        var mIsAuthError: Boolean = false
        var mIsServerError: Boolean = false
        var mIsNetworkError: Boolean = false

        @Throws(NetworkErrorException::class)
        override fun loginSync(username: String, password: String): LoginHttpEndpointSync.EndpointResult {
            mUsername = username
            mPassword = password
            return if (mIsGeneralError) {
                LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.GENERAL_ERROR, "")
            } else if (mIsAuthError) {
                LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.AUTH_ERROR, "")
            } else if (mIsServerError) {
                LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.SERVER_ERROR, "")
            } else if (mIsNetworkError) {
                throw NetworkErrorException()
            } else {
                LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.SUCCESS, "authToken")
            }
        }
    }

     class AuthTokenCacheTd: AuthTokenCache {

         var mAuthToken = ""

         override fun getAuthToken(): String {
             return mAuthToken
         }

        override fun cacheAuthToken(authToken: String) {
            this.mAuthToken = authToken
        }
    }

     class EventBusPosterTd : EventBusPoster {
        lateinit var mEvent: Any
        var mInteractionsCount: Int = 0

      override fun postEvent(event: Any) {
            mInteractionsCount++
            mEvent = event
        }
    }

}