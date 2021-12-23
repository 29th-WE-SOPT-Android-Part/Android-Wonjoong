package kr.wonjoong.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.wonjoong.data.model.AutoLoginData

@Database(entities = [AutoLoginData::class], version = 1)
abstract class SoptDatabase: RoomDatabase() {
    abstract fun soptDAO(): SoptDAO
}