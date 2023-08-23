package com.fitareq.assessmentapphpl.data.api
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun userLogin(
    )

    @POST("register")
    suspend fun userRegister(
    )
}