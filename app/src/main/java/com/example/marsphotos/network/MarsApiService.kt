package com.example.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET


// Define a private constant to hold the base URL of the web service
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

/** Create a Retrofit object using the builder pattern */
val retrofit = Retrofit.Builder() //starts the process of creating a Retrofit object.

    // Set the base URL for all API requests made through this Retrofit instance
    .baseUrl(BASE_URL)

    // Add a converter factory to handle response data conversion
    .addConverterFactory(ScalarsConverterFactory.create())  // This converter handles String responses

    // Build the Retrofit object with the specified configuration
    .build()
/**
 *  Explain the [ScalersConvertFactory]
 *  This converter factory is used for responses that are plain text or simple strings.
 *  It doesn't parse the response into objects, but simply returns the raw String content.
 *  This is suitable for APIs that return basic text data and don't involve complex data structures.
 */


/**
 *  an service interface called [MarsApiService] that defines how Retrofit talks
 *  to the web server using HTTP requests.
 */
interface MarsApiService {

    // to get the photos through http request
    @GET("photos")
    fun getPhotos(): String

}

/**
 * singleton object for creating once and for all  & exposing it to the rest of the App as calling create method
 * on retrofit object is very expensive on resource bt recommend using DI instead of singleton pattern for good practice
 */
object MarsApi{
    // making a variable which will hold the reference to the future created implementation of the [MarsAPiService] interface
   val retrofitService: MarsApiService by lazy {
       /** Create an implementation of the API endpoints defined by the service [MarsApiService] interface.*/
       retrofit.create(MarsApiService::class.java)
   }
}
