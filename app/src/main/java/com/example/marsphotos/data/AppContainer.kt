package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val marsPhotoRepository: MarsPhotoRepository
}

/**
 * we are building, configuring retrofit object  and creating the retrofitService(implementation of MarsApi)
 * in this appContainer for passing that object in our [marsPhotoRepository] dependency
 *
 * we are doing it for injecting the [marsPhotoRepository] to the [MarsViewModel] for making it loosely coupled
 * independent of [marsPhotoRepository] object
 */
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

        /**
         * Create an implementation of the API endpoints defined by the service
         * [MarsApiService] interface
         */
        retrofit.create(MarsApiService::class.java)
    }

    /**
     * we are passing the retrofitService which is marsApiService Implementation for http requests so that later we
     *      can call http request on this passed retrofitService variable as it gives us more controll and also allow us
     *      to pass  retrofitService test object (for mimicking the http request through fake data) in the repository for testing
     *
     */
    override val marsPhotoRepository: MarsPhotoRepository by lazy {
        NetworkMarsPhotoRepository(retrofitService)
    }

}