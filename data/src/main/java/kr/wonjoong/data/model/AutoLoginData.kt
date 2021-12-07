package kr.wonjoong.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AutoLoginData")
data class AutoLoginData(
    val isAutoLoginState: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)