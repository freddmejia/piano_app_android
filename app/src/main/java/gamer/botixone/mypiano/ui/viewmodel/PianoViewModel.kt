package gamer.botixone.mypiano.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gamer.botixone.mypiano.data.PianoMediaHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PianoViewModel @Inject constructor(
    private val mediaHandler: PianoMediaHandler
): ViewModel() {

    fun playKeyBy(key: String) = viewModelScope.launch  {
        mediaHandler.playKey(key)
    }

    fun playKeyBy(key: Int) = viewModelScope.launch  {
        mediaHandler.playKey(key)
    }
}