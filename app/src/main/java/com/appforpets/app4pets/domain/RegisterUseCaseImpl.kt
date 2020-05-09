package com.appforpets.app4pets.domain

import com.appforpets.app4pets.data.remote.AuthService
import com.appforpets.app4pets.data.remote.models.RegisterRequest
import com.appforpets.app4pets.domain.exception.InvalidParamException
import com.appforpets.app4pets.domain.exception.NoInternetConnectionException
import com.appforpets.app4pets.helpers.checkInternetConnection.InternetConnectionHelper
import com.appforpets.app4pets.models.User
import io.reactivex.Completable
import io.reactivex.Observable

class RegisterUseCaseImpl(private val authService: AuthService,
                          private val internetConnectionHelper: InternetConnectionHelper
) : RegisterUseCase {


    override fun createAccount(email: String, name: String, password: String) : Observable<Boolean> {
        return validateParamsDm(email, name , password )
            .andThen (generateAuthUserRequest(email, name, password))
            .flatMap { return@flatMap requestToServer(it)  }
            .flatMap { return@flatMap checkResponse(it) }

    }


    private fun validateParamsDm(email: String,name: String, password: String): Completable {

        return if (email.isEmpty()) {
            Completable.error(InvalidParamException("email", "Param code is empty"))
        } else if (name.isEmpty()) {
            Completable.error(InvalidParamException("name", "Param code is empty"))
        }else if (password.isEmpty()) {
            Completable.error(InvalidParamException("password", "Param code is empty"))
        }else{
            Completable.complete()
        }
    }


    private fun generateAuthUserRequest(email: String, name: String, password: String): Observable<RegisterRequest> {

        val userRequest = RegisterRequest(email, name, password)



        return Observable.just(userRequest)
    }

    private fun requestToServer(registerRequest: RegisterRequest): Observable<User> {

        return if(internetConnectionHelper.checkInternetConnection()) {
            authService.register(registerRequest)
        } else {
            Observable.error(NoInternetConnectionException())
        }
    }

    private fun checkResponse(user: User): Observable<Boolean> {

        return when {
            user!=null  -> Observable.just(true)

            else -> Observable.error(Exception())
        }
    }
}