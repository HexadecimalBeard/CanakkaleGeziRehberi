package com.hexadecimal.canakkalegezirehberi.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hexadecimal.canakkalegezirehberi.model.MonumentsEntity


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-01 - 13:11

@Dao
interface MonumentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewMonument(monumentsEntity: MonumentsEntity)

    @Delete
    fun deleteMonument(monumentsEntity: MonumentsEntity)

    @Update
    fun updateMonument(monumentsEntity: MonumentsEntity)

    @Query("SELECT * FROM MONUMENTSTABLE")
    fun getAllMonumentsList(): LiveData<List<MonumentsEntity>>

    @Query("SELECT * FROM MonumentsTable WHERE _id LIKE :id")
    fun findSingleMonument(id: Long): MonumentsEntity

    @Query("DELETE FROM MonumentsTable")
    fun deleteAllTable()
}