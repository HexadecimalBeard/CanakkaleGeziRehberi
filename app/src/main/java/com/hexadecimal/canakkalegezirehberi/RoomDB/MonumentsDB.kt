package com.hexadecimal.canakkalegezirehberi.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hexadecimal.canakkalegezirehberi.dao.MonumentsDao
import com.hexadecimal.canakkalegezirehberi.model.MonumentsEntity


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-01 - 13:24

// yerel veritabanım için RoomDB yapısını kullandım
// temelde SQLite üzerinde oluşturulan bir katman gibi davranıyor
// daha rahat bir geliştirme ortamı sağlıyor bizlere
// RoomDB üç yapıdan oluşmakta
// bunlardan biri bu DB yapısı
// DB yapısı olduğunu sınıf isminin başında kullandığımız @DATABASE anotation ı ile tanımladık

// hangi entity ler kullanilacaksa onu verdik, birden fazla tablon varsa class ismi sonuna
// virgul koyup diger tabloyu yaratan class ismini ver
@Database(entities = [MonumentsEntity::class], version = 1)
abstract class MonumentsDB : RoomDatabase() {

    abstract fun getMonumentsDao(): MonumentsDao

    // bir singleton yapi hazirladik, instance'in sadece bir defa olusturulmasi icin
    companion object {

        private var INSTANCE: MonumentsDB? = null

        fun getInstance(context: Context): MonumentsDB? {
            // synchronized parantezi icindeki islemi sadece ayni anda bir thread yapar
            synchronized(MonumentsDB::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        // yaratilacak olan database e isim verdik
                        MonumentsDB::class.java, "Monuments_Info_Database"
                    )
                        // db yaratildigi anda var olan verileri eklemek icin buraya metod yazilabilir
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            // bu instance ile DAO ya eriseceksin ve icindeki metodlari kullanabileceksin
            return INSTANCE
        }
    }
}