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


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = FirstFragmentArgs.fromBundle(requireArguments())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        binding.tvWynikF1.text =  "${binding.tvWynikF1.text}\n ${args.data}"

        binding.btnInF1.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(binding.editTextTextPersonNameF1.text.toString()))
//            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }
}