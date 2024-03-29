package com.iramarjunior.parrot.modules.authentication.network

import com.iramarjunior.parrot.modules.authentication.model.UserResponse
import com.iramarjunior.parrot.modules.authentication.model.UserWrapper
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthenticationAPI {

    @POST("/usuario/login")
    fun requestLogin(@Body user: UserWrapper): Call<UserResponse>

    @POST("/usuario")
    fun requestRegisterUser(@Body user: UserWrapper): Observable<Any>

}