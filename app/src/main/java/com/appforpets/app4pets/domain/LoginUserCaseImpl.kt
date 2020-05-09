package com.appforpets.app4pets.domain

import com.appforpets.app4pets.data.local.CredentialsDao
import com.appforpets.app4pets.data.remote.AuthService
import com.appforpets.app4pets.data.remote.models.LoginRequest
import com.appforpets.app4pets.data.remote.models.LoginResponse
import com.appforpets.app4pets.domain.exception.InvalidParamException
import com.appforpets.app4pets.domain.exception.NoInternetConnectionException
import com.appforpets.app4pets.helpers.checkInternetConnection.InternetConnectionHelper
import com.appforpets.app4pets.models.User
import io.reactivex.Completable
import io.reactivex.Observable

class LoginUserCaseImpl(private val authService: AuthService,
                        private val internetConnectionHelper: InternetConnectionHelper,
                        private val credentialsDao: CredentialsDao): LoginUserCase {


    override fun login(email: String, password: String) : Observable<User> {
        return validateParamsDm(email, password )
            .andThen (generateAuthUserRequest(email, password))
            .flatMap { return@flatMap requestToServer(it)  }
            .flatMap { return@flatMap checkResponse(it) }
            .flatMap { return@flatMap saveCredentials(it) }
    }


    private fun validateParamsDm(email: String, password: String): Completable {

        return if (email.isEmpty()) {
            Completable.error(InvalidParamException("email", "Param code is empty"))
        } else if (password.isEmpty()) {
            Completable.error(InvalidParamException("password", "Param code is empty"))
        }else{
            Completable.complete()
        }
    }

    private fun generateAuthUserRequest(email: String, password: String): Observable<LoginRequest> {

        val userRequest = LoginRequest(email, password)



        return Observable.just(userRequest)
    }

    private fun requestToServer(loginRequest: LoginRequest): Observable<LoginResponse> {

        return if(internetConnectionHelper.checkInternetConnection()) {
            authService.login(loginRequest)
        } else {
            Observable.error(NoInternetConnectionException())
        }
    }

    private fun checkResponse(loginResponse: LoginResponse): Observable<LoginResponse> {

        return when {
            loginResponse.token!=null  -> Observable.just(loginResponse)

            else -> Observable.error(Exception())
        }
    }

    private fun saveCredentials(credential: LoginResponse): Observable<User> {

        return if (credentialsDao.save(credential.token!!)) {
            Observable.just(credential.user)
        } else {
            Observable.error(Exception())
        }
    }

}