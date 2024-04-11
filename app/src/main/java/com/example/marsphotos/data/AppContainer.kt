package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val marsPhotoRepository: MarsPhotoRepository
}

class DefaultAppContainer: AppContainer{


    // Define a private constant to hold the base URL of the web service
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"


    /** Create a Retrofit object using the builder pattern */
    val retrofit = Retrofit.Builder() //starts the process of creating a Retrofit object.
        // Set the base URL for all API requests made through this Retrofit instance
        .baseUrl(baseUrl)
        /**
         *  Add a converter factory to handle response data conversion.
         *  This converter handles serialization of  responses for json mediaType
         */
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        // Build the Retrofit object with the specified configuration
        .build()

    /**
     * making a lazy variable to save resources which will hold the reference to
     * the future created implementation of the [MarsAPiService] interface
     */
     private val retrofitService: MarsApiService by lazy {
        /** Create an implementation of the API endpoints defined by the service [MarsApiService] interface.*/
        retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotoRepository: MarsPhotoRepository by lazy {
        NetworkMarsPhotoRepository(retrofitService)
    }

}