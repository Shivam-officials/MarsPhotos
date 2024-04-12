package com.example.marsphotos.fake

import com.example.marsphotos.network.model.MarsPhoto

/**
 * the fake data that would be returned
 *
 * the data stored in the objects does not necessarily need to be representative of the
 * data that the API returns. In other words, you don't need to include valid IDs or URLs.
 */
object FakeDataSource {
    const val idOne = "img1"
    const val idTwo = "img2"

    const val imgOne = "url1"
    const val imgTwo = "url2"

    val photoList = listOf(
        MarsPhoto(
            id = idOne,
            imgSrc = imgOne
        ),
        MarsPhoto(
            id = idTwo,
            imgSrc = imgTwo
        )
    )
}