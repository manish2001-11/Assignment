package com.mkdev.assignment.fragments

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.mkdev.assignment.R


import com.mkdev.assignment.databinding.FragmentSubmitBinding
import com.mkdev.assignment.model.FormData
import com.mkdev.assignment.viewModel.shareViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class SubmitFragment : Fragment() {
    val sharedViewModel: shareViewModel by activityViewModels()

lateinit var binding: FragmentSubmitBinding
    lateinit var  fusedLocationClient : FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubmitBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val submit = view.findViewById<Button>(R.id.submitData)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        submit.setOnClickListener {

//
//            sharedViewModel.stopRecording()
//            sharedViewModel.saveAudioMetadataToJson()

            val submissionTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()).format(Date())
            // getLocation()

            if (submissionTime != null) {
                sharedViewModel.addData("submitTime",submissionTime)
                //   (activity as MainActivity).goToNextPage()
               // Toast.makeText(requireContext(), "$submissionTime", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please enter a time not create", Toast.LENGTH_SHORT).show()
            }
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat .checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireContext() as Activity,
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION), 100)

            }else{


                fusedLocationClient.lastLocation.addOnSuccessListener{location : Location? ->
                    if (location != null) {
                        val latitude = location?.latitude.toString()
                        val longitude = location?.longitude.toString()
                        val data = "$latitude $longitude"

                        binding.textView2.text ="Data save in json file"
                        sharedViewModel?.addData("gpa",data)



                    } else {

                    }
                }
            }



            sharedViewModel.formData.observe(viewLifecycleOwner) { data ->

               // binding.textView2.text = data["gpa"].toString()
                val formDataObject = FormData(data)
                val gson = Gson()
                val jsonString = gson.toJson(formDataObject)

                // Create Assignment folder and save formData.json
                val rootDir = requireContext().filesDir
                val assignmentDir = File(rootDir, "Assignment")
                if (!assignmentDir.exists()) {
                    assignmentDir.mkdirs()
                }

                val file = File(assignmentDir, "formData.json")
                file.writeText(jsonString)

                Toast.makeText(requireContext(),"${file.toString()}", Toast.LENGTH_LONG).show()
            }

        }



    }





}