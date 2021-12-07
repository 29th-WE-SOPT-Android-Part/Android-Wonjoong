package kr.wonjoong.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.wonjoong.data.source.local.SoptDAO
import kr.wonjoong.data.source.local.SoptDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideSoptDataBase(@ApplicationContext applicationContext: Context): SoptDatabase {
        return Room.databaseBuilder(
            applicationContext,
            SoptDatabase::class.java,
            "sopt.db"
        ).build()
    }

    @Provides
    fun provideSoptDAO(soptDatabase: SoptDatabase): SoptDAO {
        return soptDatabase.soptDAO()
    }
}