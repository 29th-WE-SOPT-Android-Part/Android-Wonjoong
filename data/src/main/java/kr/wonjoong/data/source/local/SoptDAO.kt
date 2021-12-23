package kr.wonjoong.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.wonjoong.data.model.AutoLoginData

@Dao
interface SoptDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAutoLoginData(autoLoginData: AutoLoginData)

    @Query("UPDATE AutoLoginData SET isAutoLoginState = :autoLoginData WHERE id = 1")
    suspend fun updateAutoLoginData(autoLoginData: Boolean)

    @Query("SELECT * FROM AutoLoginData WHERE id = 1")
    suspend fun getAutoLoginData(): AutoLoginData
}