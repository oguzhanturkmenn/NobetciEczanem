package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.databinding.FragmentAboutAppBinding
import com.oguzhanturkmen.mypharmacyonduty.databinding.FragmentSplashBinding


class AboutAppFragment : Fragment() {
    private var _binding: FragmentAboutAppBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}