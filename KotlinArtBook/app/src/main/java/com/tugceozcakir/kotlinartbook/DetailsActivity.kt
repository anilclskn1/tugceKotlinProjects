package com.tugceozcakir.kotlinartbook

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PathPermission
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.tugceozcakir.kotlinartbook.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.sql.Statement
import java.util.jar.Manifest


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedBitmap: Bitmap? = null
    private lateinit var database: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = this.openOrCreateDatabase("Arts", Context.MODE_PRIVATE, null)

        registerLauncher()

        val intent = intent
        val info = intent.getStringExtra("info")

        if (info.equals("new")) {
            binding.artNameText.setText("")
            binding.artistNameText.setText("")
            binding.yearText.setText("")
            binding.save.visibility = View.VISIBLE

            val selectedImageBackground = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.select)
            binding.imageView.setImageBitmap(selectedImageBackground)
        } else {
            binding.save.visibility = View.INVISIBLE
            //secilen sanat eserinin id'sini almak icin:
            val selectedId = intent.getIntExtra("id", 1)
            val cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?", arrayOf(selectedId.toString()))

            val artNameIx = cursor.getColumnIndex("artname")
            val artistNameIx = cursor.getColumnIndex("artistname")
            val yearIx = cursor.getColumnIndex("year")
            val imageIx = cursor.getColumnIndex("image")

            while (cursor.moveToNext()) {
                binding.artNameText.setText(cursor.getString(artNameIx))
                binding.artistNameText.setText(cursor.getString(artistNameIx))
                binding.yearText.setText(cursor.getString(yearIx))

                val byteArray = cursor.getBlob(imageIx)
                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                binding.imageView.setImageBitmap(bitmap)
            }
            cursor.close()
        }

    }

    fun saveButtonClicked(view: View) {

        val artName = binding.artNameText.text.toString()
        val artistName = binding.artistNameText.text.toString()
        val year = binding.yearText.text.toString()


        if (selectedBitmap != null) {
            val smallerBitmap = makeSmallerBitmap(selectedBitmap!!, 300)
            val outputStream = ByteArrayOutputStream()

            smallerBitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
            val byteArray = outputStream.toByteArray()

            //Veri tabanlar?? i??lemlerini try-catch i??inde yap??yoruz:
            try {
                //val database = openOrCreateDatabase("Arts", MODE_PRIVATE, null)
                database.execSQL("CREATE TABLE IF NOT EXISTS arts(id INTEGER PRIMARY KEY, artname VARCHAR, artistname VARCHAR, year VARCHAR, image BLOB)")
                val sqlString = "INSERT INTO arts (artname, artistname, year, image) VALUES (?, ?, ?, ?)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1, artName)
                statement.bindString(2, artistName)
                statement.bindString(3, year)
                statement.bindBlob(4, byteArray)
                statement.execute()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val intent = Intent(this@DetailsActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun makeSmallerBitmap(image: Bitmap, maximumSize: Int): Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRadio: Double = width.toDouble() / height.toDouble()
        if (bitmapRadio > 1) {
            //landscape
            width = maximumSize
            val scaledHeight = width / bitmapRadio
            height = scaledHeight.toInt()
        } else {
            //portraid
            height = maximumSize
            val scaledWidth = height * bitmapRadio
            width = scaledWidth.toInt()

        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    fun selectImage(view: View) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //rationale
                Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give permission", View.OnClickListener {
                        //request permission
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }).show()
            } else {
                //request permission
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }

        } else {
            //intent
            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)

        }
    }

    //Galeriye gitmek ve galeriden g??rseli se??mek i??in:
    private fun registerLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.resultCode == RESULT_OK) {
                    val intentFromResult = result.data
                    /*
                    binding.imageView.setImageURI(imageData)
                    Diyerek bu URI'yi alabiliriz ve bu veriyi kullan??c??ya g??sterebiliriz.
                    Fakat bizim bu veriyi alarak kendi bitmap'imizi olu??turmam??z laz??m
                    ????nk?? biz bu bitmap'i al??p SQLite'a kaydedece??iz.Kaydetmeden ??nce de k??????ltme i??lemine tabi tutmam??z gerek.
                     */
                        if (intentFromResult != null) {
                            val imageData = intentFromResult.data

                            //Hata ????karsa yakalamak i??in:
                            //ImageDecoder: URI'y??, 1 ve 0'lar?? g??rsel yapabilmek i??in bir s??n??f.ImageDecoder.createSource ile bunu g??rebiliriz.
                            // Bu da bizden i??erik ????z??mleyicisi (contentResolver) ister. Bir de URI ister bu da: imageData.
                            try {
                                if (Build.VERSION.SDK_INT >= 28) {
                                    //Se??ilen g??rsel neyse art??k imageView'umuzda g??stermesi i??in:
                                    val source = ImageDecoder.createSource(this@DetailsActivity.contentResolver, imageData!!)
                                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                                    binding.imageView.setImageBitmap(selectedBitmap)
                                } else {
                                    //28'den b??y??k veya e??it de??ilse:
                                    selectedBitmap =
                                        MediaStore.Images.Media.getBitmap(this@DetailsActivity.contentResolver, imageData)
                                    binding.imageView.setImageBitmap(selectedBitmap)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }

                }

                //??zin istemek i??in:
                permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                        if (result) {
                            //permission granted
                            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            activityResultLauncher.launch(intentToGallery)
                        } else {
                            //permission denied
                            Toast.makeText(this, "Permission needed", Toast.LENGTH_LONG).show()
                        }

                    }
            }
    }
}