package com.example.a160420124_marcelladivaviorent_healthcareumc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.a160420124_marcelladivaviorent_healthcareumc.R
import com.example.a160420124_marcelladivaviorent_healthcareumc.util.loadImage
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.DrugDetailViewModel
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.EventDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class EventDetailFragment : Fragment() {
    private lateinit var eventDetailViewModel: EventDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val idEvent = EventDetailFragmentArgs.fromBundle(requireArguments()).eventID
            eventDetailViewModel = ViewModelProvider(this).get(EventDetailViewModel::class.java)
            eventDetailViewModel.fetch(idEvent)
        }

        val imageViewKegiatan = view.findViewById<ImageView>(R.id.imageViewKegiatan)
        val textViewNamaKegiatan = view.findViewById<TextView>(R.id.textViewNamaKegiatan)
        val textDetailKegiatan = view.findViewById<TextView>(R.id.textDetailKegiatan)
        val textHariKegiatan = view.findViewById<TextView>(R.id.textHariKegiatan)
        val textPukulKegiatan = view.findViewById<TextView>(R.id.textPukulKegiatan)
        val textBiayaKegiatan = view.findViewById<TextView>(R.id.textBiayaKegiatan)
        val progressLoadKegiatan = view.findViewById<ProgressBar>(R.id.progressLoadKegiatan)
        val btnJoin = view.findViewById<Button>(R.id.btnJoin)

        eventDetailViewModel.eventLD.observe(viewLifecycleOwner){eventDetail ->
            imageViewKegiatan.loadImage(eventDetail.photoUrl,progressLoadKegiatan)
            textViewNamaKegiatan.setText(eventDetail.name.toString())
            textDetailKegiatan.setText(eventDetail.detail.toString())
            textHariKegiatan.setText(eventDetail.hari.toString())
            textPukulKegiatan.setText(eventDetail.pukul.toString())
            textBiayaKegiatan.setText(eventDetail.biaya.toString())

//            var event = eventDetail
//            btnJoin.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_notifications_24)
//                    }
//            }
        }
    }
}