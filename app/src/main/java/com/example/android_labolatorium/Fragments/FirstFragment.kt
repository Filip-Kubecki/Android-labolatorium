package com.example.android_labolatorium.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android_labolatorium.R
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

//        binding.radioMath.setOnClickListener {
//            if (binding.radioMath.isChecked){
//                binding.editTextTextPersonNameF1.inputType = InputType.TYPE_CLASS_NUMBER
//
//            }else{
//                binding.editTextTextPersonNameF1.inputType = InputType.TYPE_CLASS_TEXT
//            }
//        }



        binding.btnInF1.setOnClickListener {
            if (binding.radioMath.isChecked){
                if (binding.radioSubAdd.isChecked){
                    findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSubFragment(
                        binding.editTextTextPersonNameF1.text.toString()
                    ))
                }else{
                    findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToAddFragment(
                        binding.editTextTextPersonNameF1.text.toString()
                    ))
                }
            }else{
                findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    binding.editTextTextPersonNameF1.text.toString()
                ))
            }
        }

        return binding.root
    }
}