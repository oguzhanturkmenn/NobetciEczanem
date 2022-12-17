package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.adapter.PharmacyAdapter
import com.oguzhanturkmen.mypharmacyonduty.databinding.FragmentPharmacyBinding
import com.oguzhanturkmen.mypharmacyonduty.util.CityModel
import com.oguzhanturkmen.mypharmacyonduty.util.getJsonDataFromAsset
import com.oguzhanturkmen.mypharmacyonduty.viewmodel.PharmacyViewModel
import kotlinx.android.synthetic.main.fragment_pharmacy.*


class PharmacyFragment : Fragment(R.layout.fragment_pharmacy),AdapterView.OnItemSelectedListener {
    private val viewModel : PharmacyViewModel by activityViewModels()
    //SPINNER
    lateinit var adapter1: ArrayAdapter<String>
    lateinit var adapter2: ArrayAdapter<String>
    private var spinnerCity = ArrayList<String>()
    private var spinnerDistrict = ArrayList<String>()
    private var cities: List<CityModel>? = null
    private var selectedCityString :String? = null
    //SPINNER
    private lateinit var adapter: PharmacyAdapter

    private var selectedCity:String? =null
    private var selectedDistrict:String? =null

    private var _binding: FragmentPharmacyBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPharmacyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        observeLoading()
        spinnerProcess(view)
        searchButton()

        if (selectedCity!=null || selectedDistrict!=null){
            viewModel.getDatas(selectedDistrict!!,selectedCity!!)
        }
    }

    private fun observeData(){
        viewModel.pharmacyList.observe(viewLifecycleOwner) {
                it.let {
                    val animationController: LayoutAnimationController =
                        AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation)
                    rvEczane.layoutAnimation = animationController
                    adapter = PharmacyAdapter(it)
                    rvEczane.adapter = adapter
                    rvEczane.layoutManager = LinearLayoutManager(context)
                    rvEczane.setHasFixedSize(true)
                }
        }

    }

    private fun observeLoading(){
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.searchButton.isEnabled = !it
            binding.isLoadingProgress.visibility = if (it) View.VISIBLE else View.GONE
            if (it){
                binding.rvEczane.visibility = View.GONE
            }else{
                binding.rvEczane.visibility = View.VISIBLE
            }
        }
    }

    private fun spinnerProcess(view: View) {
        val jsonFileString = context?.let { getJsonDataFromAsset(it,"il_ilce.json") }
        val gson = Gson()
        val listCityType = object : TypeToken<List<CityModel>>() {}.type
        cities = gson.fromJson(jsonFileString, listCityType)
        cities?.let {
            for (i in 0 until cities!!.size) {

                spinnerCity.add(cities!![i].il)
            }
        }
        adapter1 = ArrayAdapter(view.context, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,spinnerCity)
        adapter1.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        spinner1.adapter = adapter1
        spinner1.onItemSelectedListener = this@PharmacyFragment

        adapter2 = ArrayAdapter(view.context,  com.google.android.material.R.layout.support_simple_spinner_dropdown_item, spinnerDistrict)
        adapter2.setDropDownViewResource( com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        spinner2.adapter = adapter2


    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        spinnerDistrict.clear()
        val secilenSpinner = p0?.getItemAtPosition(p2)
        selectedCityString = secilenSpinner.toString()

        try {
            cities?.forEach {
                if (secilenSpinner == it.il) {
                    it.ilceleri.forEach { ilce ->
                        spinnerDistrict.add(ilce)
                    }
                    adapter2.notifyDataSetChanged()
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }


    private fun searchButton(){
        searchButton.setOnClickListener {
            val il = spinner1.selectedItem.toString()
            val ilce = spinner2.selectedItem.toString()
            viewModel.getDatas(ilce, il)

        }

    }

}