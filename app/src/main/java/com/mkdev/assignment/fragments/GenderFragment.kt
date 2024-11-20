package com.mkdev.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mkdev.assignment.R
import com.mkdev.assignment.databinding.FragmentGenderBinding
import com.mkdev.assignment.viewModel.shareViewModel

class GenderFragment : Fragment(R.layout.fragment_gender) {
    lateinit var binding: FragmentGenderBinding
    val sharedViewModel: shareViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
       binding = FragmentGenderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Define a map with keys as display names and values as associated data


//        sharedViewModel.startRecording()

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.ageFragment)
        }

        val dropdownItems = mapOf(
            "FEMALE" to "female",
            "MALE" to "male",
            "OTHERS" to "others"
        )

        // Add a default "null" option as the first item
        val optionsList = listOf("Select Gender") + dropdownItems.keys.toList()

        // Set up the ArrayAdapter for the Spinner
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, optionsList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // val spinner: Spinner = findViewById(R.id.spinner)
        binding.spinner.adapter = adapter

        // Set the onItemSelectedListener to handle item selection, including the "null" default
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    // Handle null/default option
                    Toast.makeText(requireContext(), "No selection", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedKey = optionsList[position]
                    val selectedValue = dropdownItems[selectedKey]

                    sharedViewModel.addData("Q1",selectedValue.toString())
                    Toast.makeText(
                        requireContext(),
                        "Selected Value: $selectedValue",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case when nothing is selected, if needed
            }
        }


    }
}
