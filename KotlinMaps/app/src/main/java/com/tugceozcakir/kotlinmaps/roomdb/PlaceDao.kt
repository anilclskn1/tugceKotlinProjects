package com.tugceozcakir.kotlinmaps.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tugceozcakir.kotlinmaps.model.Place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

//veriye erisim objesi

@Dao
interface PlaceDao {
    @Query("SELECT * FROM Place")
    fun getAll(): Flowable<List<Place>>

    @Insert
    fun insert (place: Place) : Completable

    @Delete
    //completable: tamamlanabilir gibi görünecek ve asenkron olarak yapmamiza olanak taniyacak
    fun delete (place: Place) : Completable
}