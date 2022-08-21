package com.cameraxtutorial.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.lifecycle.ProcessCameraProvider
import com.cameraxtutorial.databinding.ActivityMainBinding
import com.google.common.util.concurrent.ListenableFuture
import java.io.File


class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        var goBack : () -> Unit = {}


        lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    }

    private fun getFileName() = "JPEG_${System.currentTimeMillis()}"
    fun getFile() =  File(externalMediaDirs[0], getFileName())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        setContentView(binding.root)

    }



    override fun onBackPressed() {

        goBack()
    }


}

