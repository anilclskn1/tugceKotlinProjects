package com.tugceozcakir.kotlinmaps.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.tugceozcakir.kotlinmaps.R
import com.tugceozcakir.kotlinmaps.adapter.PlaceAdapter
import com.tugceozcakir.kotlinmaps.databinding.ActivityMainBinding
import com.tugceozcakir.kotlinmaps.databinding.RecyclerRowBinding
import com.tugceozcakir.kotlinmaps.model.Place
import com.tugceozcakir.kotlinmaps.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()
    private lateinit var places: ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

        places = ArrayList()

        val db = Room.databaseBuilder(applicationContext,PlaceDatabase::class.java, "Places")
            .build()
        val placeDao = db.placeDao()

        compositeDisposable.add(
            placeDao.getAll()
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleRespose)
        )

    }

    private fun handleRespose(placeList: List<Place>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PlaceAdapter(placeList)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuyu buraya bagliyoruz.
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.place_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //menuden herhangi bir eleman secilirse ne olacagini belirliyoruz.
        if(item.itemId == R.id.add_place){
            //diger activityye gidecegiz.
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}