package com.mkdev.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mkdev.assignment.MainActivity
import com.mkdev.assignment.R

import com.mkdev.assignment.databinding.FragmentAgeBinding
import com.mkdev.assignment.viewModel.shareViewModel


class AgeFragment : Fragment(R.layout.fragment_age) {

    val shareViewModel :shareViewModel by activityViewModels()
 lateinit var binding: FragmentAgeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentAgeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnNext.setOnClickListener{
            val age = binding.editAge.text.toString()
            if (age != null) {
                shareViewModel.addData("Q2",age)
                //   (activity as MainActivity).goToNextPage()
            } else {
                Toast.makeText(requireContext(), "Please enter a valid age", Toast.LENGTH_SHORT).show()
            }
            findNavController().navigate(R.id.action_ageFragment_to_imageFragment)
        }







        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_ageFragment_to_genderFragment)
        }
    }
}