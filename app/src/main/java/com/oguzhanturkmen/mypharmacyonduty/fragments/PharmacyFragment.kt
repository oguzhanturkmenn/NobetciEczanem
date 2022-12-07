package com.oguzhanturkmen.mypharmacyonduty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.adapter.PharmacyAdapter
import com.oguzhanturkmen.mypharmacyonduty.viewmodel.PharmacyViewModel
import kotlinx.android.synthetic.main.fragment_pharmacy.*
import kotlinx.android.synthetic.main.my_toolbar.*

class PharmacyFragment : Fragment() {
    private val viewModel : PharmacyViewModel by activityViewModels()

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

        observeData()
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
    fun searchButton(){
        searchButton.setOnClickListener {
            val ilce = districtText.text.toString().trim()
            val il = provinceText.text.toString().trim()
            viewModel.getDatas(ilce,il)
        }

    }
}