package gamer.botixone.mypiano.ui.menu

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import gamer.botixone.mypiano.presentation.viewmodels.PianoViewModel

@Composable
fun PianoScreen(
    modifier: Modifier = Modifier,
    octaves: Int = 1,
    whiteKeyNote: (String) -> Unit,
    blackKeyNote: (Int) -> Unit,
) {
    val whiteKeysPerOctave = listOf("DO", "RE", "MI", "FA", "SOL", "LA", "SI")
    val blackKeysPatternPerOctave = listOf(true, true, false, true, true, true, false)

    val whiteKeys = List(octaves) { whiteKeysPerOctave }.flatten()
    val blackKeysPattern = List(octaves) { blackKeysPatternPerOctave }.flatten()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
    ) {

        Row(modifier = Modifier.fillMaxSize()) {
            whiteKeys.forEach { key ->

                PianoKey(
                    isBlack = false,
                    onPlay = {
                        whiteKeyNote(key)
                        //Log.d("Piano", "Tocaste nota blanca: $key")
                             },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )

                /*Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.White)
                        .border(1.dp, Color.Black)
                        .clickable {
                            Log.e("WHITEKEY", "PianoScreenV3: ${key}", )
                        }
                )*/
            }
        }


        Row(modifier = Modifier.fillMaxSize()) {
            blackKeysPattern.forEachIndexed { index, hasBlackKey ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    DrawBlackKey(index, hasBlackKey, blackKeyNote)
                }
            }
        }
    }
}

@Composable
fun DrawBlackKey(index: Int, hasBlackKey: Boolean, blackKeyNote: (Int) -> Unit) {
    if (!hasBlackKey) return

    val blackKeyOffsetsInOctave = mapOf(
        0 to 5.5f,
        1 to 6.5f,
        3 to 5f,
        4 to 6f,
        5 to 7f
    )

    val positionInOctave = index % 7
    val octaveOffset = index / 7
    val globalOffsetPosition = blackKeyOffsetsInOctave[positionInOctave] ?: return

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val whiteKeyWidth = maxWidth / 7
        val blackKeyWidth = whiteKeyWidth * 5f

        val totalOffset = (octaveOffset * 1 + globalOffsetPosition) * whiteKeyWidth - (blackKeyWidth / 2f)

        PianoKey(
            isBlack = true,
            onPlay = {
                blackKeyNote(index)
                //Log.d("Piano", "Tocaste nota negra índice: $index")
                     },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = totalOffset)
                .width(blackKeyWidth)
                .fillMaxSize(0.6f)
        )

        /*Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = totalOffset)
                .width(blackKeyWidth)
                .fillMaxHeight(0.6f)
                .background(Color.Black, shape = RoundedCornerShape(4.dp))
                .clickable {
                    Log.e("BLACKKEY", "PianoScreenV3: ${index}", )
                }
        )*/
    }
}

@Composable
fun PianoKey(
    isBlack: Boolean,
    onPlay: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val bg = when {
        isPressed && isBlack -> Color(0xFF444444)
        isPressed && !isBlack -> Color(0xFFCCCCCC)
        isBlack -> Color.Black
        else -> Color.White
    }

    Box(
        modifier = modifier
            .background(bg, shape = RoundedCornerShape(if (isBlack) 4.dp else 0.dp))
            .border(1.dp, Color.Black)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onPlay() }
            )
    )

    /*
    *
    * Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = totalOffset)
                .width(blackKeyWidth)
                .fillMaxHeight(0.6f)
                .background(Color.Black, shape = RoundedCornerShape(4.dp))
                .clickable {
                    Log.e("BLACKKEY", "PianoScreenV3: ${index}", )
                }
        )
    * */

}


@Preview
@Composable
fun ShowItemBlackButtons() {

    PianoScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp),
        whiteKeyNote = { note -> Log.d("Piano", "Tocaste nota blanca: $note") },
        blackKeyNote = { index -> Log.d("Piano", "Tocaste nota negra índice: $index")},
    )
}