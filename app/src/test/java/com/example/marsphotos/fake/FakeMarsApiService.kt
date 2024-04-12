package com.example.marsphotos.fake

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.model.MarsPhoto

/**
 * as repository depend on the apiService to return the data so i created the fake ApiService
 * to return the data that would be passed in the repository for testing only
 */
//fake retrofit service (a test object) for passing in the repository so that it would return the data
class FakeMarsApiService: MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photoList
    }
}