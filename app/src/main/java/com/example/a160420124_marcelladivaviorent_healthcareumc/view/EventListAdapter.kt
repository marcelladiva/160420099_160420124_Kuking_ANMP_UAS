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
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.Event
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage

class EventListAdapter(val eventList:ArrayList<Event>):RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {
    class EventViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.event_list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val textEventID = holder.view.findViewById<TextView>(R.id.textEventID)
        val textEventName = holder.view.findViewById<TextView>(R.id.textEventName)
        val btnJoin = holder.view.findViewById<Button>(R.id.btnEventDetail)

        val eventID = eventList[position].id.toString()
        textEventID.text = eventList[position].id
        textEventName.text = eventList[position].name

        btnJoin.setOnClickListener {
            val action = EventListFragmentDirections.actionEventDetail(eventID)
            Navigation.findNavController(it).navigate(action)
        }

        var imageViewEvent = holder.view.findViewById<ImageView>(R.id.imageViewEvent)
        var progressBarEvent = holder.view.findViewById<ProgressBar>(R.id.progressBarEvent)
        imageViewEvent.loadImage(eventList[position].photoUrl, progressBarEvent)
    }

        fun updateEventList(newEventList: ArrayList<Event>) {
        eventList.clear()
        eventList.addAll(newEventList)
        notifyDataSetChanged()
    }
}