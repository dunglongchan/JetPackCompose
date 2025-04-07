package testing.jetpack.compose.ui.screen.listuser

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _users = mutableStateListOf<LocalUser>()
    val users: List<LocalUser> get() = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val listUser = userRepository.getAllUser()
            _users.addAll(listUser)
        }
    }

    fun addData() {
        viewModelScope.launch {
            val listUser = userRepository.getAllUser()
            _users.addAll(listUser) // Add new users
        }
    }

    fun shuffleData() {
        _users.shuffle()
    }
}