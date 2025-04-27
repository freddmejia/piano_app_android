package gamer.botixone.mypiano.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PianoMediaHandler @Inject constructor(
    private val loader: PianoSoundLoader
) {
    private val soundPool = loader.getSoundPool()

    fun playKey(name: String) {
        val soundId = loader.getSoundId(name) ?: return
        soundPool.play(
            soundId,
            /* leftVolume */ 1f,
            /* rightVolume */ 1f,
            /* priority */ 1,
            /* loop */ 0,
            /* rate */ 1f
        )
    }

    fun playKey(index: Int) {
        val whiteNames   = listOf("DO","RE","MI","FA","SOL","LA","SI")
        val blackPattern = listOf(true,true,false,true,true,true,false)
        val blackNames   = listOf("DO#","RE#","","FA#","SOL#","LA#","")

        val pos = index % 7
        if (blackPattern[pos]) {
            playKey(blackNames[pos])
        } else {
            playKey(whiteNames[pos])
        }
    }
}