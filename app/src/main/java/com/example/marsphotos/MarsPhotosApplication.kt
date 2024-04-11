//package com.example.marsphotos
//
//import android.app.Application
//import com.example.marsphotos.data.AppContainer
//import com.example.marsphotos.data.DefaultAppContainer
//
//class MarsPhotosApplication: Application() {
//
//    lateinit var container: AppContainer
//    override fun onCreate() {
//        super.onCreate()
//        var container = DefaultAppContainer()
//    }
//}

package com.example.marsphotos

import android.app.Application
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer

/**
 *  This class declaration defines a new class named MarsPhotosApplication.
 *  As it extends the Application class, it becomes the entry point for your entire application.
 *
 */
class MarsPhotosApplication : Application() {

    /**
     *  The "lateinit" keyword indicates that the variable will be initialized later
     *      in the application's lifecycle to avoid potential null pointer exceptions during the early stages.
     *
     */
    lateinit var container: AppContainer

    /**
     * This method is called by the Android system when the application is first created.
     *      It overrides the onCreate method inherited from the base Application class.
     *
     */
        override fun onCreate() {
        /**
         * Call the superclass's onCreate method to ensure any initialization steps defined there are executed.
         */
        super.onCreate()

        // Initialize the "container" variable with an instance of the DefaultAppContainer class.
        container = DefaultAppContainer()
    }
}
//