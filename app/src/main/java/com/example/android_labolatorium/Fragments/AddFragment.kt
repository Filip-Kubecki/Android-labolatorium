package com.example.android_labolatorium.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.android_labolatorium.R
import com.example.android_labolatorium.databinding.ActivityMainBinding
import com.example.android_labolatorium.databinding.FragmentAddBinding
import com.example.android_labolatorium.databinding.FragmentFirstBinding
import com.example.android_labolatorium.databinding.FragmentSecondBinding
import com.example.android_labolatorium.databinding.FragmentThirdBinding


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = AddFragmentArgs.fromBundle(requireArguments())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        binding.tvWynikF3.text =  args.value.toString()

        binding.btnInF3.setOnClickListener {
//            findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToFirstFragment(
                (args.value.toString().toInt()+binding.editTextTextPersonNameF3.text.toString().toInt()).toString()
            ))
        }
        return binding.root
    }
}