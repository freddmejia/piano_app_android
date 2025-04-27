package gamer.botixone.mypiano.data

import android.content.Context
import android.media.SoundPool
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import gamer.botixone.mypiano.R

@Singleton
class PianoSoundLoader @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(10)
        .build()

    private val soundMap: Map<String, Int> by lazy { loadAllSounds() }

    private fun loadAllSounds(): Map<String, Int> {
        return mapOf(
            "DO"   to load(R.raw.do4),
            "DO#"  to load(R.raw.do_sharp4),
            "RE"   to load(R.raw.re4),
            "RE#"  to load(R.raw.re_sharp4),
            "MI"   to load(R.raw.mi4),
            "FA"   to load(R.raw.fa4),
            "FA#"  to load(R.raw.fa_sharp4),
            "SOL"  to load(R.raw.sol4),
            "SOL#" to load(R.raw.sol_sharp4),
            "LA"   to load(R.raw.la4),
            "LA#"  to load(R.raw.la_sharp4),
            "SI"   to load(R.raw.si4)
        )
    }

    private fun load(@RawRes resId: Int): Int =
        soundPool.load(context, resId,1)

    fun getSoundPool(): SoundPool = soundPool

    fun getSoundId(name: String): Int? = soundMap[name]
}