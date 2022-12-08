package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyModel
import com.oguzhanturkmen.mypharmacyonduty.viewmodel.PharmacyViewModel
import kotlinx.android.synthetic.main.fragment_pharmacy_details.*


class PharmacyDetailsFragment : Fragment() {
    private lateinit var _view: View
    private val viewModel : PharmacyViewModel by activityViewModels()
    private lateinit var pharmacyModel: PharmacyModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_pharmacy_details, container, false)

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pharmacyModel = PharmacyDetailsFragmentArgs.fromBundle(it).pharmacyId
        }
        observeDetailsData()
        call()
        goToMaps()
    }

    private fun observeDetailsData() {
        viewModel.pharmacyList.observe(viewLifecycleOwner, Observer {pharmacyModel
            pharmacyModel.let {
                eczDetailName.text = pharmacyModel.EczaneAdi
                eczAdresName.text = pharmacyModel.Adresi
                eczTelNo.text = pharmacyModel.Telefon
            }
        })
    }

    fun goToMaps(){
        goToMapsImage.setOnClickListener {
            val action = PharmacyDetailsFragmentDirections.actionPharmacyDetailsFragmentToMapsFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun call() {


        phoneImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${pharmacyModel.Telefon}")
            startActivity(intent)



        }
    }
}