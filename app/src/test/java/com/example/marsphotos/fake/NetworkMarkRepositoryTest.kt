
import com.example.marsphotos.data.NetworkMarsPhotoRepository
import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeMarsApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * testing the [NetworkMarsPhotoRepository] by passing the fake apiService which returns the fake dataSource
 */
class NetworkMarkRepositoryTest {

    // checking the getPhotos method of repository
    @Test
    fun marsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {

        val repositoryTest = NetworkMarsPhotoRepository(
            marsApiService = FakeMarsApiService()
        )

        // asserting that the repository returned the data from mentioned dataSource
        assertEquals( FakeDataSource.photoList,repositoryTest.getPhotos() )
    }
}