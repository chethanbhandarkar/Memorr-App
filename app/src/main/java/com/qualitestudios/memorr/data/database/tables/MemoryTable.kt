package com.qualitestudios.memorr.data.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemoryTable(
@PrimaryKey
    val date:String,
    val title:String,
    val message:String

)
