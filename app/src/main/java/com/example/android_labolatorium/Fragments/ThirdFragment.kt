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
import com.example.android_labolatorium.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = ThirdFragmentArgs.fromBundle(requireArguments())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)

        binding.tvWynikF3.text =  "${binding.tvWynikF3.text}\n ${args.data}"

        binding.btnInF3.setOnClickListener {
//            findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
            findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToFirstFragment(binding.editTextTextPersonNameF3.text.toString()))
        }
        return binding.root
    }
}