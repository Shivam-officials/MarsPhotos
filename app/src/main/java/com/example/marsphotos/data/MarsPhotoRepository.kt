package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.model.MarsPhoto

/**
 * interface of  repository which exposes the data and centralise the data to all the data sources
 * making interface bcz it will ease the future work of adding new functionality by adding only in interface
 * it will force us to add it in the all implementation of its repository
 */
interface MarsPhotoRepository {
    suspend fun getPhotos(): List<MarsPhoto>

}

/**
 * implementation of [MarsPhotoRepository] interface
 */
class NetworkMarsPhotoRepository : MarsPhotoRepository{
    override suspend fun getPhotos(): List<MarsPhoto> {
        return MarsApi.retrofitService.getPhotos()
    }
}