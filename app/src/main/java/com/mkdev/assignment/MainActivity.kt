package com.mkdev.assignment

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.mkdev.assignment.databinding.ActivityMainBinding
import com.mkdev.assignment.viewModel.shareViewModel
import kotlin.collections.get

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var shareViewModel:shareViewModel? = null
    lateinit var  fusedLocationClient : FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



    }

}