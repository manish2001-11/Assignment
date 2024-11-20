package com.mkdev.assignment.fragments


import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager

import android.graphics.Bitmap
import android.hardware.Camera
import android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mkdev.assignment.R

import com.mkdev.assignment.databinding.FragmentImageBinding
import com.mkdev.assignment.viewModel.shareViewModel
import java.io.File


class ImageFragment : Fragment() {
lateinit var binding: FragmentImageBinding

private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()){
    binding.imageView.setImageURI(imageUri)
}
    lateinit var imageUri: Uri

 val shareViewModel: shareViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
   binding = FragmentImageBinding.inflate(inflater)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_imageFragment_to_submitFragment)
        }
        super.onViewCreated(view, savedInstanceState)


//        val launcher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == RESULT_OK) {
//                    val image = result.data?.extras?.get("data") as Bitmap
//                    binding.imageView.setImageBitmap(image)
//
////

        imageUri = createImageUri()!!
        binding.btnCapture.setOnClickListener{
            contract.launch(imageUri)

        }


                }
    private fun createImageUri(): Uri? {
        val image = File(requireContext().filesDir,"camera_photos.png")
        shareViewModel.addData("image",image.toString())
        return FileProvider.getUriForFile(requireContext(),"com.mkdev.assignment.fileProvider",
            image)


    }

            }

//        binding.btnCapture.setOnClickListener {
//            val cameraOpen = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            launcher.launch(cameraOpen)
//        }

//    }
//







