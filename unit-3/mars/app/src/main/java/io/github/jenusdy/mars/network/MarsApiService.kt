package io.github.jenusdy.mars.network

import io.github.jenusdy.mars.model.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}