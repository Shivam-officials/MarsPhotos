import com.example.marsphotos.data.NetworkMarsPhotoRepository
import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeMarsApiService
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * testing the [NetworkMarsPhotoRepository] by passing the fake apiService which returns the fake dataSource
 */
class NetworkMarkRepositoryTest {

    /** testing the getPhotos() method of the NetworkMarsPhotosRepository class */
    @Test
    fun marsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {

        val repositoryTest = NetworkMarsPhotoRepository(
            marsApiService = FakeMarsApiService()
        )

        // asserting that the repository returned the data from mentioned dataSource
        TestCase.assertEquals(FakeDataSource.photoList, repositoryTest.getPhotos())
    }
}