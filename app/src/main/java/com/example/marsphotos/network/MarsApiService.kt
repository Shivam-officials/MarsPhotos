package com.example.marsphotos.network

import com.example.marsphotos.network.model.MarsPhoto
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET


/**
 *  an service interface called [MarsApiService] that defines how Retrofit talks
 *  to the web server using HTTP requests.
 */
interface MarsApiService {

    // to get the photos through http request
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>

}

/**
 * singleton object for creating once and for all  & exposing it to the rest of the App as calling create method
 * on retrofit object is very expensive on resource bt recommend using DI instead of singleton pattern for good practice
 */
//object MarsApi{

// we dont need it anymore

//}
