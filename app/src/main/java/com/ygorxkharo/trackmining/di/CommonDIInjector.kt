package com.ygorxkharo.trackmining.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ygorxkharo.obey.trackmining.DefaultLibraryTracksProvider
import com.ygorxkharo.obey.trackmining.api.LibraryTracksHttpClient
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.HTTPApi
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.SpotifyTracksClient
import com.ygorxkharo.obey.trackmining.api.spotify.apiclient.mapper.LibraryTrackMapper
import com.ygorxkharo.obey.trackmining.platform.MusicTracksSource
import com.ygorxkharo.obey.trackmining.platform.spotify.SpotifyTracksMiner
import com.ygorxkharo.obey.utilities.threading.CoroutineContextProvider
import com.ygorxkharo.obey.utilities.threading.DefaultCoroutineContextProvider
import com.ygorxkharo.trackmining.LibraryTracksProvider
import com.ygorxkharo.trackmining.MineLibraryTracksUseCase
import com.ygorxkharo.trackmining.MineLibraryTracksUseCaseImpl
import com.ygorxkharo.trackmining.platform.MusicLibraryTracksMiner
import org.kodein.di.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SpotifyTrackMinerModuleBuilder {

    fun build() = DI.Module("") {

        val spotifyAPIBaseUrl = "https://api.spotify.com/"
        bind<HTTPApi>() with provider {
            val converterFactoryInstance: MoshiConverterFactory = instance()

            Retrofit.Builder()
                .baseUrl(spotifyAPIBaseUrl)
                .addConverterFactory(converterFactoryInstance)
                .build()
                .create(HTTPApi::class.java)
        }

        bind<LibraryTrackMapper>() with provider {
            LibraryTrackMapper()
        }

        bind<LibraryTracksHttpClient>(MusicTracksSource.SPOTIFY) with provider {
            val libraryTrackMapper: LibraryTrackMapper = instance()
            val coroutineContextProvider: CoroutineContextProvider = instance()
            val tracksApi: HTTPApi = instance()

            SpotifyTracksClient(tracksApi, coroutineContextProvider, libraryTrackMapper)
        }

        bind<MusicLibraryTracksMiner>(MusicTracksSource.SPOTIFY) with singleton {
            val tracksClient: LibraryTracksHttpClient = instance(MusicTracksSource.SPOTIFY)
            val trackResultLimit = 1
            SpotifyTracksMiner(tracksClient, trackResultLimit)
        }
    }
}

class LibraryTrackMiningModule {

    fun build() = DI.Module("") {
        bind<LibraryTracksProvider<String>>() with provider {
            val spotifyMiner: MusicLibraryTracksMiner = instance(MusicTracksSource.SPOTIFY)

            val libraryTrackMiners = mapOf(
                MusicTracksSource.SPOTIFY.sourceName to spotifyMiner
            )

            DefaultLibraryTracksProvider(libraryTrackMiners)
        }

        bind<MineLibraryTracksUseCase>() with provider {
            val libraryTracksProvider: LibraryTracksProvider<String> = instance()
            MineLibraryTracksUseCaseImpl(libraryTracksProvider)
        }
    }
}

class NetworkingModuleBuilder {

    fun build() = DI.Module("") {

        bind<KotlinJsonAdapterFactory>() with provider {
            KotlinJsonAdapterFactory()
        }

        bind<Moshi>() with provider {
            val jsonAdapterFactory: KotlinJsonAdapterFactory = instance()
            Moshi.Builder().add(jsonAdapterFactory).build()
        }

        bind<MoshiConverterFactory>() with provider {
            val moshiJSONConverter: Moshi = instance()
            MoshiConverterFactory.create(moshiJSONConverter)
        }
    }
}

class ThreadingModuleBuilder {

    fun build() = DI.Module("threading") {
        bind<CoroutineContextProvider>() with provider { DefaultCoroutineContextProvider() }
    }
}

object CommonDIInjector {

    val diContainer =  DI.lazy {
        import(sharedDIModule) // To avoid breaking changes in Android and iOS, we'll import the shared DI module until changes have been made
    }

    val sharedDIModule = DI.Module("") {
        importAll(
            ThreadingModuleBuilder().build(),
            NetworkingModuleBuilder().build(),
            SpotifyTrackMinerModuleBuilder().build(),
            LibraryTrackMiningModule().build()
        )
    }
}