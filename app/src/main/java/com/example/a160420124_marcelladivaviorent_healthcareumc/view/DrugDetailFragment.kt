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
import com.google.android.material.bottomnavigation.BottomNavigationView

class DrugDetailFragment : Fragment() {
    private lateinit var drugDetailViewModel: DrugDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drug_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val idDrug = DrugDetailFragmentArgs.fromBundle(requireArguments()).drugID
            drugDetailViewModel = ViewModelProvider(this).get(DrugDetailViewModel::class.java)
            drugDetailViewModel.fetch(idDrug)
        }

        val imageViewObat = view.findViewById<ImageView>(R.id.imageViewObat)
        val textNamaObat = view.findViewById<TextView>(R.id.textNamaObat)
        val textDetailObat = view.findViewById<TextView>(R.id.textDetailObat)
        val textDosisObat = view.findViewById<TextView>(R.id.textDosisObat)
        val textHargaObat = view.findViewById<TextView>(R.id.textHargaObat)
        val progressLoadObat = view.findViewById<ProgressBar>(R.id.progressLoadObat)
        val btnCart = view.findViewById<Button>(R.id.btnCart)

        drugDetailViewModel.drugLD.observe(viewLifecycleOwner){drugDetail ->
            imageViewObat.loadImage(drugDetail.photoUrl,progressLoadObat)
            textNamaObat.setText(drugDetail.name.toString())
            textDetailObat.setText(drugDetail.detail.toString())
            textDosisObat.setText(drugDetail.dosis.toString())
            textHargaObat.setText(drugDetail.harga.toString())

//            var drug = drugDetail
//            btnCart.setOnClickListener {
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