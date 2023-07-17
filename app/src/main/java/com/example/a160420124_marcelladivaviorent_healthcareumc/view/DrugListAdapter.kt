package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.DrugListItemBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.FacilityListItemBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Drug
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage

class DrugListAdapter(val drugList:ArrayList<Drug>):RecyclerView.Adapter<DrugListAdapter.DrugViewHolder>(), DrugItemLayoutInterface {
    class DrugViewHolder(var view: DrugListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DrugListItemBinding>(inflater,R.layout.drug_list_item,parent,false)
        return DrugViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drugList.size
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        holder.view.drug = drugList[position]
        holder.view.listener = this
//        val textDrugID = holder.view.findViewById<TextView>(R.id.textDrugID)
//        val textDrugName = holder.view.findViewById<TextView>(R.id.textDrugName)
//        val btnDrugDetail = holder.view.findViewById<Button>(R.id.btnDrugDetail)
//
//        val drugID = drugList[position].id.toString()
//        textDrugID.text = drugList[position].id
//        textDrugName.text = drugList[position].name
//
//        btnDrugDetail.setOnClickListener {
//            val action = DrugListFragmentDirections.actionDrugDetail(drugID)
//            Navigation.findNavController(it).navigate(action)
//        }

//        var imageViewDrug = holder.view.findViewById<ImageView>(R.id.imageViewDrug)
//        var progressBarDrug = holder.view.findViewById<ProgressBar>(R.id.progressBarDrug)
//        imageViewDrug.loadImage(drugList[position].photoUrl, progressBarDrug)
    }

    fun updateDrugList(newDrugList: List<Drug>) {
        drugList.clear()
        drugList.addAll(newDrugList)
        notifyDataSetChanged()
    }

    override fun onButtonClick(v: View) {
        val uuid = v.tag.toString()
        val action = DrugListFragmentDirections.actionDrugDetail(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}