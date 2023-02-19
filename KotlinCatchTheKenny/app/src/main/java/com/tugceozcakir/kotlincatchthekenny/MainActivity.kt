package com.tugceozcakir.kotlincatchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.RandomAccess
import java.util.logging.Handler as Handler1
import java.util.logging.Handler as JavaUtilLoggingHandler
import kotlin.random.Random as Random

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable{ }
    var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)

        hideImages()


        object: CountDownTimer(20000, 1000){
            override fun onTick(p0: Long) {
                timeText.text = "Time: ${p0/1000}"
            }

            override fun onFinish() {
                timeText.text = "SURENIZ BITTI"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game?")
                alert.setPositiveButton("Yes"){dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog, which ->
                    Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_SHORT).show()
                }
                    .show()
            }

        }.start()
    }


    fun hideImages(){

        runnable = object: Runnable {
            override fun run() {
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = java.util.Random()
                val randomIndex = random.nextInt(16)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 400)
            }
        }
        handler.post(runnable)
    }


    fun increaseScore(view: View){
            score = score + 1
            scoreText.text = "SCORE: $score"
    }
}