package gamer.botixone.mypiano.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gamer.botixone.mypiano.data.PianoMediaHandler
import gamer.botixone.mypiano.data.PianoSoundLoader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePianoSoundLoader(
        @ApplicationContext ctx: Context
    ): PianoSoundLoader = PianoSoundLoader(ctx)

    @Provides
    @Singleton
    fun providePianoMediaHandler(
        loader: PianoSoundLoader
    ): PianoMediaHandler = PianoMediaHandler(loader)
}