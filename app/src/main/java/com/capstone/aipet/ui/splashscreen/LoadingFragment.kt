package com.capstone.aipet.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.aipet.DashboardActivity
import com.capstone.aipet.R


class LoadingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val SPLASH_TIME_OUT = 10000

        Handler(Looper.getMainLooper()).postDelayed({
              val intent = Intent(requireContext(), DashboardActivity::class.java)
              startActivity(intent)
        }, SPLASH_TIME_OUT.toLong())
    }


}