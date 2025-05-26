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
import com.example.android_labolatorium.databinding.FragmentFirstBinding
import com.example.android_labolatorium.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = SecondFragmentArgs.fromBundle(requireArguments())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        binding.tvWynikF2.text =  "${binding.tvWynikF2.text}\n ${args.data}"

        binding.btnInF2.setOnClickListener {
//            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
            findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToThirdFragment(binding.editTextTextPersonNameF2.text.toString()))
        }
        return binding.root
    }
}