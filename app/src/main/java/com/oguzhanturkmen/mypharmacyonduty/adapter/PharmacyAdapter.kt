package com.oguzhanturkmen.mypharmacyonduty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanturkmen.mypharmacyonduty.databinding.PharmacyRowBinding
import com.oguzhanturkmen.mypharmacyonduty.fragments.PharmacyFragmentDirections
import com.oguzhanturkmen.mypharmacyonduty.models.PharmacyModel

class PharmacyAdapter(private val eczList: List<PharmacyModel>) : RecyclerView.Adapter<PharmacyAdapter.PharmacyHolder>() {

    class PharmacyHolder(var v: PharmacyRowBinding) : RecyclerView.ViewHolder(v.root) {
        fun bind(p: PharmacyModel) {
            v.model = p
            v.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PharmacyRowBinding.inflate(inflater)
        return PharmacyHolder(binding)
    }

    override fun onBindViewHolder(holder: PharmacyHolder, position: Int) {
        val ecz = eczList[position]
        holder.bind(ecz)
        holder.itemView.setOnClickListener {
            val action = PharmacyFragmentDirections.actionPharmacyFragmentToPharmacyDetailsFragment(ecz)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return eczList.size
    }
}
