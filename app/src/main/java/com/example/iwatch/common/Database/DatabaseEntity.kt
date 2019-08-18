package com.robusta.tripsappteam1.common.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Creating Databse Objects
 */

@Entity
data class MovieFavourites constructor(
    @PrimaryKey()
    val id: Int ,
    val MovieName: String,
    val imageMovie:String
)




