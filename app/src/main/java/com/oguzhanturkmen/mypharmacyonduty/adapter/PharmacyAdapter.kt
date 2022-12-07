package com.oguzhanturkmen.mypharmacyonduty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.mypharmacyonduty.R
import com.oguzhanturkmen.mypharmacyonduty.fragments.PharmacyFragmentDirections
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyModel
import kotlinx.android.synthetic.main.pharmacy_row.view.*

class PharmacyAdapter(private val eczList : List<PharmacyModel>) : RecyclerView.Adapter<PharmacyAdapter.PharmacyHolder>() {


    class PharmacyHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pharmacy_row,parent,false)
        return PharmacyHolder(view)
    }

    override fun onBindViewHolder(holder: PharmacyHolder, position: Int) {
        val ecz = eczList[position]

        holder.itemView.apply {
            pharmacyNameText.text = ecz.EczaneAdi
            addressText.text = ecz.Adresi
            phoneText.text = ecz.Telefon
        }

        holder.itemView.setOnClickListener {
            val action = PharmacyFragmentDirections.actionPharmacyFragmentToPharmacyDetailsFragment(ecz)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return eczList.size
    }
}