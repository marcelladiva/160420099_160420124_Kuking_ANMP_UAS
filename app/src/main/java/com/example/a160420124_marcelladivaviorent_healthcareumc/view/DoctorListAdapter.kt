package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.content.Intent
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
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Doctor
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage

    class DoctorListAdapter(val doctorList:ArrayList<Doctor>):RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>() {
    class DoctorViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val textDoctorID = holder.view.findViewById<TextView>(R.id.textDoctorID)
        val textDoctorName = holder.view.findViewById<TextView>(R.id.textDoctorName)
        val btnSchedule = holder.view.findViewById<Button>(R.id.btnPracticeSchedule)

        val doctorID = doctorList[position].id.toString()
        textDoctorID.text = doctorList[position].id
        textDoctorName.text = doctorList[position].name

        btnSchedule.setOnClickListener {
            val action = DoctorListFragmentDirections.actionDoctorDetail(doctorID)
            Navigation.findNavController(it).navigate(action)
        }

        var imageViewDoctor = holder.view.findViewById<ImageView>(R.id.imageViewDoctor)
        var progressBarDoctor = holder.view.findViewById<ProgressBar>(R.id.progressBarDoctor)
        imageViewDoctor.loadImage(doctorList[position].photoUrl, progressBarDoctor)
    }

    fun updateDoctorList(newDoctorList: List<Doctor>) {
        doctorList.clear()
        doctorList.addAll(newDoctorList)
        notifyDataSetChanged()
    }
}