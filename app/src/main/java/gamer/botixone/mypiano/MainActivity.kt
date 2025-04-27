package gamer.botixone.mypiano

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import gamer.botixone.mypiano.presentation.viewmodels.PianoViewModel
import gamer.botixone.mypiano.ui.menu.PianoScreen
import gamer.botixone.mypiano.ui.theme.MyPianoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val pianoViewModel: PianoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyPianoTheme {
                PianoScreen(
                    modifier = Modifier.fillMaxSize(),
                    whiteKeyNote = { note ->
                        pianoViewModel.playKeyBy(key = note)
                        Log.d("Piano", "Tocaste nota blanca: $note")
                                   },
                    blackKeyNote = { index ->
                        pianoViewModel.playKeyBy(key = index)
                        Log.d("Piano", "Tocaste nota negra índice: $index")
                                   },
                    )

                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    PianoScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
            }
        }
    }
}
