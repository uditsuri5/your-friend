package com.example.homescreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homescreen.Test.TestInstructions
import com.example.homescreen.databinding.FirstFragmentBinding
import com.example.homescreen.doctorsRV.MainActivityDoctors
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class FirstFragment : Fragment(R.layout.first_fragment) {
    private lateinit var mdAView:AdView
    private var mInterstitialAd:InterstitialAd?=null


    private lateinit var binding : FirstFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val View = inflater.inflate(R.layout.first_fragment,container,false)
        mdAView = View.findViewById(R.id.adView)

        val adRequest = AdRequest.Builder().build()
        mdAView.loadAd(adRequest)

        MobileAds.initialize(requireContext())

        loadInterstitialAd()






        binding = FirstFragmentBinding.inflate(inflater, container, false)
        binding.imageButton1.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
    }
        binding.imageButton2.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
        }
        binding.imageButton3.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
        }
        binding.imageButton4.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
        }
        binding.imageButton5.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
        }
        binding.imageButton6.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
        }
        binding.imageButton7.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
        }
        binding.imageButton8.setOnClickListener{
            val Intent = Intent(requireContext(), MainActivityDoctors::class.java)
            startActivity(Intent)
        }
        binding.btnTest.setOnClickListener{
            val Intent = Intent(requireContext(), TestInstructions::class.java)
            startActivity(Intent)
        }




        return binding.root


    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            requireContext(),
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })
    }


}