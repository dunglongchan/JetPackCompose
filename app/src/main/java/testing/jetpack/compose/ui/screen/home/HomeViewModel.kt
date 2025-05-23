package testing.jetpack.compose.ui.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    val localUser = HomeArgs(savedStateHandle).user
}