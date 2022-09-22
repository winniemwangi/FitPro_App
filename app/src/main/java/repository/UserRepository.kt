package repository

import com.winnie.workoutlog.api.APIClient
import com.winnie.workoutlog.api.ApiInterface
import com.winnie.workoutlog.models.LoginRequest
import com.winnie.workoutlog.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = APIClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>
    = withContext(Dispatchers.IO){
        val response = apiClient.loginUser(loginRequest)
        return@withContext response
    }
}