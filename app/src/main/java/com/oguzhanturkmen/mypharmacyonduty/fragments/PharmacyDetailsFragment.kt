package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.databinding.FragmentPharmacyDetailsBinding
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyModel
import com.oguzhanturkmen.mypharmacyonduty.viewmodel.PharmacyViewModel
import kotlinx.android.synthetic.main.fragment_pharmacy_details.*

class PharmacyDetailsFragment : Fragment(R.layout.fragment_pharmacy_details) {
    private val viewModel: PharmacyViewModel by activityViewModels()
    private lateinit var pharmacyModel: PharmacyModel
    private var _binding: FragmentPharmacyDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            pharmacyModel = PharmacyDetailsFragmentArgs.fromBundle(it).pharmacyId!!
        }
        observeDetailsData()
        call()
        goToMaps()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPharmacyDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observeDetailsData() {
        viewModel.pharmacyList.observe(viewLifecycleOwner) {
            pharmacyModel
            pharmacyModel.let {
                binding.model = it
            }
        }
    }

    private fun goToMaps() {
        binding.goToMapsJmage.setOnClickListener {
            val lat = pharmacyModel.latitude
            val long = pharmacyModel.longitude
            val uri = Uri.parse("geo:$lat,$long?q=${pharmacyModel.EczaneAdi}")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            ContextCompat.startActivity(requireContext(), mapIntent, null)
        }
    }

    private fun call() {
        binding.callimage.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${pharmacyModel.Telefon}")
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
