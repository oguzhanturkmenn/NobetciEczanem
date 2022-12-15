package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ak.nobetcieczane.util.CityModel
import com.ak.nobetcieczane.util.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.adapter.PharmacyAdapter
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyModel
import com.oguzhanturkmen.mypharmacyonduty.util.showSnackbar
import com.oguzhanturkmen.mypharmacyonduty.util.toast
import com.oguzhanturkmen.mypharmacyonduty.viewmodel.PharmacyViewModel
import kotlinx.android.synthetic.main.fragment_pharmacy.*
import kotlinx.android.synthetic.main.my_toolbar.*

class PharmacyFragment : Fragment(),AdapterView.OnItemSelectedListener {
    private val viewModel : PharmacyViewModel by activityViewModels()
    //SPINNER
    lateinit var adapter1: ArrayAdapter<String>
    lateinit var adapter2: ArrayAdapter<String>
    private var spinneril = ArrayList<String>()
    private var spinnerilce = ArrayList<String>()
    private var cities: List<CityModel>? = null
    private var secilenIlString :String? = null
    private var secilenIlceString:String?=null
    //SPINNER
    private lateinit var adapter: PharmacyAdapter

    private var secilenil:String? =null
    private var secilenilce:String? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pharmacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { toast(it,"Hoşgeldiniz, lütfen il ve ilçe seçip aratınız.") }

        observeData()
        spinnerProcess(view)
        searchButton()

        if (secilenil!=null || secilenilce!=null){
            viewModel.getDatas(secilenilce!!,secilenil!!)
        }

    }

    private fun observeData(){
        viewModel.pharmacyList.observe(viewLifecycleOwner, Observer {
                it.let {

                    adapter = PharmacyAdapter(it)
                    rv_eczane.adapter = adapter
                    rv_eczane.layoutManager = LinearLayoutManager(context)
                    rv_eczane.setHasFixedSize(true)
                }
        })
    }

    private fun spinnerProcess(view: View) {
        val jsonFileString = context?.let { getJsonDataFromAsset(it,"il_ilce.json") }
        val gson = Gson()
        val listCityType = object : TypeToken<List<CityModel>>() {}.type
        cities = gson.fromJson(jsonFileString, listCityType)
        cities?.let {
            for (i in 0 until cities!!.size) {

                spinneril.add(cities!![i].il)
            }
        }
        adapter1 = ArrayAdapter(view.context, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,spinneril)
        adapter1.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        spinner1.adapter = adapter1
        spinner1.onItemSelectedListener = this@PharmacyFragment

        adapter2 = ArrayAdapter(view.context,  com.google.android.material.R.layout.support_simple_spinner_dropdown_item, spinnerilce)
        adapter2.setDropDownViewResource( com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        spinner2.adapter = adapter2


    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        spinnerilce.clear()
        val secilenSpinner = p0?.getItemAtPosition(p2)
        secilenIlString = secilenSpinner.toString()

        try {
            cities?.forEach {
                if (secilenSpinner == it.il) {
                    it.ilceleri.forEach { ilce ->
                        spinnerilce.add(ilce)
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


    fun searchButton(){
        searchButton.setOnClickListener {
            val il = spinner1.selectedItem.toString()
            val ilce = spinner2.selectedItem.toString()
            viewModel.getDatas(ilce, il)


        }

    }

}