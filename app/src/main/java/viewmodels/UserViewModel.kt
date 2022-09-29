package viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winnie.workoutlog.models.LoginRequest
import com.winnie.workoutlog.models.LoginResponse
import com.winnie.workoutlog.models.RegisterRequest
import com.winnie.workoutlog.models.RegisterResponse
import kotlinx.coroutines.launch
import repository.UserRepository

class UserViewModel: ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()
    var registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()

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

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.registerUser(registerRequest)
            if (response.isSuccessful) {
                registerResponseLiveData.postValue(response.body())
            } else {
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }
}