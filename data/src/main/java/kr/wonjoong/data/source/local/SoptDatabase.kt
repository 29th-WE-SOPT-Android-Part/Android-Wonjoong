package kr.wonjoong.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.wonjoong.data.model.AutoLoginData
import kr.wonjoong.data.source.RoomConverters

@Database(entities = [AutoLoginData::class], version = 1)
@TypeConverters(RoomConverters::class)
abstract class SoptDatabase: RoomDatabase() {
    abstract fun soptDAO(): SoptDAO
}