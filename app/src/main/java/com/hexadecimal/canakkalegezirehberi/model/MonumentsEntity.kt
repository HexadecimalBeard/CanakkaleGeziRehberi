package com.hexadecimal.canakkalegezirehberi.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Created by Melih KOK
// kokmelih@gmail.com
// 2019-04-30 - 11:34

@Entity(tableName = "MonumentsTable")
data class MonumentsEntity(

    @ColumnInfo
    @PrimaryKey(autoGenerate = true) @NonNull
    val _id: Long = 1,

    @ColumnInfo(name = "Anit_Ismi")
    val anitIsmi: String,

    @ColumnInfo(name = "Anit_Aciklama")
    val anitAciklama: String,

    @ColumnInfo(name = "Anit_Resim")
    val anitResim: Int,

    @ColumnInfo(name = "Anit_Konum")
    val anitKonum: String,

    @ColumnInfo(name = "Anit_Koordinat_Lat")
    val anitKoordinatLat: Double,

    @ColumnInfo(name = "Anit_Koordinat_Long")
    val anitKoordinatLong: Double

)