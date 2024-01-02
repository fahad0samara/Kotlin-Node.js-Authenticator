package com.fahad.kotlinnodeauthenticator.di

import com.fahad.kotlinnodeauthenticator.data.repository.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  fun provideHttpClient(): HttpClient {
    return HttpClient(CIO) {
      install(ContentNegotiation) {
        json()
      }
    }
  }

  @Provides
  fun provideRegistrationRepository(client: HttpClient): RegistrationRepository {
    return RegistrationRepository(client)
  }
}

//  @Provides
//  fun provideLoginRepository(client: HttpClient): LoginRepository {
//    return LoginRepository(client)
//  }
//}
