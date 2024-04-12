/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotoRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    data object Loading : MarsUiState
    data object Error : MarsUiState
}

class MarsViewModel(
    private val marsPhotoRepository: MarsPhotoRepository
) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * MarsPhoto [List] [MutableList].
     */
      fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = try {
//                val listResult = MarsApi.retrofitService.getPhotos()

                val listResult = marsPhotoRepository.getPhotos()

                MarsUiState.Success("Success: ${listResult.size} Mars photos retrieved")
            } catch (e: IOException) {
                MarsUiState.Error
            }
        }

//        marsUiState = "Set the Mars API status response here!"
    }

    /**
     * CREATE THE CUSTOM FACTORY FOR OBJECT CREATION
     * creating an companion object for the creation of custom ViewModels factory .
     * Custom factories are used when you want to inject dependencies into your
     * ViewModel instances at the time of their creation.
     */
    // Companion object to access the factory instance from outside the class
    companion object {
        // factory variable of type ViewModelProvider.Factory to store the factory reference
        val factory: ViewModelProvider.Factory = viewModelFactory {

            // This block defines how to create a MarsViewModel instance
            /**
             * This factory, in turn, uses the provided initializer block to define
             * the steps for constructing a MarsViewModel instance with the necessary
             * dependencies retrieved from the application container.
             */
            initializer {

                /**
                 * The APPLICATION_KEY is part of the ViewModelProvider.AndroidViewModelFactory.Companion
                 * object and is used to find the app's MarsPhotosApplication object, which has the
                 * container property used to retrieve the repository used for dependency injection.
                 */
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)

                // Get the MarsPhotoRepository instance from the application container
                val marsPhotoRepository = application.container.marsPhotoRepository

                // Create a MarsViewModel instance with the retrieved repository dependency
                MarsViewModel(marsPhotoRepository = marsPhotoRepository)
            }
        }
    }

}
