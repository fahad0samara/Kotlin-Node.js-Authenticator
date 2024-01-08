package com.fahad.kotlinnodeauthenticator.di


import android.content.Context
import androidx.room.Room
import com.fahad.kotlinnodeauthenticator.data.AppDatabase
import com.fahad.kotlinnodeauthenticator.data.UserDao
import com.fahad.kotlinnodeauthenticator.data.repository.LoginRepository
import com.fahad.kotlinnodeauthenticator.data.repository.RegistrationRepository
import com.fahad.kotlinnodeauthenticator.ui.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideHttpClient(): HttpClient {
    return HttpClient(CIO) {
      install(ContentNegotiation) {
        json()
      }
    }
  }
  @Singleton
  @Provides
  fun provideContext(@ApplicationContext context: Context): Context {
    return context
  }

  @Provides
  @Singleton
  fun provideRegistrationRepository(client: HttpClient): RegistrationRepository {
    return RegistrationRepository(client)
  }

    @Provides
    @Singleton
  fun provideLoginRepository(client: HttpClient,context: Context): LoginRepository {
    return LoginRepository(client,context)
  }






  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "my_database"
    ).build()
  }

  @Provides
  fun provideUserDao(appDatabase: AppDatabase): UserDao {
    return appDatabase.userDao()
  }


  @Provides
  fun providerHomeViewModel(
    repository: LoginRepository

                            ): HomeViewModel {
    return HomeViewModel( repository)
  }






}







