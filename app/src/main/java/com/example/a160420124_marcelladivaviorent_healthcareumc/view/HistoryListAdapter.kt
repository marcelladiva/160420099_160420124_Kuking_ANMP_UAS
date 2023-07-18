package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.databinding.HistoryListItemBinding
import com.example.a160420124_marcelladivaviorent_healthcareumc.model.History

class HistoryListAdapter(val historyList:ArrayList<History>): RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(var view: HistoryListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HistoryListItemBinding>(inflater,
            R.layout.history_list_item,parent,false)
        return HistoryListAdapter.HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.view.history = historyList[position]
    }

    fun updateHistoryList(newHistoryList: List<History>) {
        historyList.clear()
        historyList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
}