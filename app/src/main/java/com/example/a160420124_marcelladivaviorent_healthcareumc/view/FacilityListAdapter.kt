package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Facility
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage

class FacilityListAdapter(val facilityList:ArrayList<Facility>):RecyclerView.Adapter<FacilityListAdapter.FacilityViewHolder>() {
    class FacilityViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.facility_list_item, parent, false)
        return FacilityListAdapter.FacilityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return facilityList.size
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val txtIdFacility = holder.view.findViewById<TextView>(R.id.txtIdFacility)
        val txtNameFac = holder.view.findViewById<TextView>(R.id.txtNameFac)
        val txtDescFacility = holder.view.findViewById<TextView>(R.id.txtDescFacility)

        txtIdFacility.text = facilityList[position].id
        txtNameFac.text = facilityList[position].name
        txtDescFacility.text = facilityList[position].description

        var imgFacility = holder.view.findViewById<ImageView>(R.id.imgFacility)
        var progressBarFacility = holder.view.findViewById<ProgressBar>(R.id.progressBarFacility)
        imgFacility.loadImage(facilityList[position].photoUrl, progressBarFacility)
    }

    fun updateFacilityList(newFacilityList: List<Facility>) {
        facilityList.clear()
        facilityList.addAll(newFacilityList)
        notifyDataSetChanged()
    }
}