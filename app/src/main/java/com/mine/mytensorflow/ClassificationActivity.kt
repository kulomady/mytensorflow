package com.mine.mytensorflow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.app.Activity
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.util.DisplayMetrics
import android.widget.Toast
import com.mine.mytensorflow.mnist.ImageUtils
import com.mine.mytensorflow.mnist.MnistClassifier
import com.mine.mytensorflow.mnist.MnistModelConfig
import com.mine.mytensorflow.mobilenet.MobilenetClassifier
import com.mine.mytensorflow.mobilenet.MobilenetModelConfig
import kotlinx.android.synthetic.main.activity_classification.*
import java.io.IOException
import java.util.*
import android.text.format.DateUtils




class ClassificationActivity : AppCompatActivity() {

    private var mnistClassifier: MnistClassifier? = null
    private var mobilenetClassifier:MobilenetClassifier? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                val intent = Intent()
                intent.putExtra("pos", 0)
                setResult(Activity.RESULT_OK, intent)
                finish()
                overridePendingTransition(0, 0)
            }
            R.id.navigation_classification -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_about -> {
                val intent = Intent()
                intent.putExtra("pos", 2)
                setResult(Activity.RESULT_OK, intent)
                finish()
                overridePendingTransition(0, 0)
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification)
        loadMnistClassifier()
        loadMobilenetClassifier()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_classification
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        btnTakePhoto.setOnClickListener { onTakePhoto() }
    }

    override fun onStart() {
        super.onStart()
        vCamera.onStart()
    }

    override fun onResume() {
        super.onResume()
        vCamera.onResume()
    }

    override fun onPause() {
        vCamera.onPause()
        super.onPause()
    }

    override fun onStop() {
        vCamera.onStop()
        super.onStop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        vCamera.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun onTakePhoto() {
        vCamera.captureImage { cameraKitView, picture -> onImageCaptured(picture) }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(0, 0)
    }

    private fun loadMnistClassifier() {
        try {
            mnistClassifier = MnistClassifier.classifier(assets, MnistModelConfig.MODEL_FILENAME)
        } catch (e: IOException) {
            Toast.makeText(this, "MNIST model couldn't be loaded. Check logs for details.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

    private fun loadMobilenetClassifier() {
        try {
            mobilenetClassifier = MobilenetClassifier.classifier(assets, MobilenetModelConfig.MODEL_FILENAME)
        } catch (e: IOException) {
            Toast.makeText(this, "mobilenet model couldn't be loaded. Check logs for details.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

    private fun onImageCaptured(picture: ByteArray) {
        val bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.size)
        val squareBitmap = ThumbnailUtils.extractThumbnail(bitmap, getScreenWidth(), getScreenWidth())
        ivPreview.setImageBitmap(squareBitmap)

//        val preprocessedImage = ImageUtils.prepareImageForClassification(squareBitmap)
//
//        mnistClassifier?.let {
//            val recognitions = it.recognizeImage(preprocessedImage)
//            tvClassification.setText(recognitions.toString())
//        }
        if(options.selectedItem == "Mobilenet") {
            val startTime = System.currentTimeMillis()
            val preprocessedImage = ImageUtils.prepareImageForClassificationMobilenet(squareBitmap)
            mobilenetClassifier?.let {
                val recognitions = it.recognizeImage(preprocessedImage)
                val endTime = System.currentTimeMillis()
                val difference = endTime - startTime
                val differenceInSeconds = difference / DateUtils.SECOND_IN_MILLIS
                val timeFormatted = DateUtils.formatElapsedTime(differenceInSeconds)
                var hasil = "hasil : \n"
                recognitions.forEach {
                    hasil = hasil + it.title + " confident \n" + it.confidence + "\n"
                }

                val output = recognitions.toString() + "waktu : " + difference
                tvClassification.text = output
            }
        }else {
            Toast.makeText(this, "Inception", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getScreenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}
