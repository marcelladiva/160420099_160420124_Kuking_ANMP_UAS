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
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.ArticleDetailViewModel
import com.example.a160420124_marcelladivaviorent_healthcareumc.viewmodel.DoctorDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class DoctorPracticeScheduleFragment : Fragment() {
    private lateinit var doctorDetailViewModel: DoctorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_practice_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val idDoctor = DoctorPracticeScheduleFragmentArgs.fromBundle(requireArguments()).doctorID
            doctorDetailViewModel = ViewModelProvider(this).get(DoctorDetailViewModel::class.java)
            doctorDetailViewModel.fetch(idDoctor)
        }

        val imageViewDokter = view.findViewById<ImageView>(R.id.imageViewDokter)
        val textNamaDokter = view.findViewById<TextView>(R.id.textNamaDokter)
        val textDetailDokter = view.findViewById<TextView>(R.id.textDetailDokter)
        val textHariPraktek = view.findViewById<TextView>(R.id.textHariPraktek)
        val textJamPraktek = view.findViewById<TextView>(R.id.textJamPraktek)
        val progressLoadDokter = view.findViewById<ProgressBar>(R.id.progressLoadDokter)

        doctorDetailViewModel.doctorLD.observe(viewLifecycleOwner){doctorDetail ->
            imageViewDokter.loadImage(doctorDetail.photoUrl,progressLoadDokter)
            textNamaDokter.setText(doctorDetail.name.toString())
            textDetailDokter.setText(doctorDetail.detail.toString())
            textHariPraktek.setText(doctorDetail.hari.toString())
            textJamPraktek.setText(doctorDetail.jam.toString())
            val btnSchedule = view.findViewById<Button>(R.id.btnSchedule)

//            var doctor = doctorDetail
//            btnSchedule.setOnClickListener {
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