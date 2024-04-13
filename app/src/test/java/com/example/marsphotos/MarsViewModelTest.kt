package com.example.marsphotos

import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeNetworkRepository
import com.example.marsphotos.rules.TestDispatcherRule
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test



class MarsViewModelTest {

    /**
     * This TestDispatcherRule simplifies managing the coroutine dispatcher during unit tests.
     * It automatically sets a test dispatcher for Dispatchers.Main before each test and resets it
     * afterward, ensuring your tests run smoothly without relying on the unavailable UI thread
     */
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /**
     * test for viewmodel getPhotos function
     */
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest{
      /**  // passed the real repository with fake api service bt codelab was saying to make a fake repository both work do as u please
        val repositoryTest = NetworkMarsPhotoRepository(
            marsApiService = FakeMarsApiService()
        )
       */

        val repositoryTest = FakeNetworkRepository()

        /**
         * we are directly able to make the viewModel class here bcz now we are directly using viewModelConstructor
         * and are not using android framework which uses viewModel() which restricts to use the initialise viewmodel
         * with the passing parameter in the constructor so we had to make the custom viewModel factory bt in test cases
         * scenarios it works and u can initialise it with the constructor just like below
         */
        val marsViewModel = MarsViewModel(
            marsPhotoRepository = repositoryTest
        )
        /**
         * MarsViewModel.getMarsPhotos() is called when the ViewModel is initialized due to init block.
         */
        assertEquals(
            MarsUiState.Success(FakeDataSource.photoList),
            marsViewModel.marsUiState
        )
    }
}

