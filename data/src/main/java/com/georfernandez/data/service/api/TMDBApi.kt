package com.georfernandez.data.service.api

import com.georfernandez.data.service.response.ActorPagingResponse
import retrofit2.Call
import retrofit2.http.GET

interface TMDBApi {
    @GET("3/person/popular")
    fun getPopularActors(): Call<ActorPagingResponse>
}
