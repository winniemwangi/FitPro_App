package viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winnie.workoutlog.models.LoginRequest
import com.winnie.workoutlog.models.LoginResponse
import kotlinx.coroutines.launch
import repository.UserRepository

class UserViewModel: ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}