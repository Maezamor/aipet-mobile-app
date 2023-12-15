package com.capstone.aipet

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.aipet.databinding.ActivityDashboardBinding
import com.capstone.aipet.ui.home.detaildog.DetailDogFragment
import com.capstone.aipet.ui.maps.MapsDetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
//    companion object {
//        const val EXTRA_FRAGMENT = "extra_fragment"
//        const val FRAGMENT_HOME = "fragment_home"
//        // Tambahkan konstanta untuk fragment lain jika diperlukan
//        // const val FRAGMENT_OTHER = "fragment_other"
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
        navView.setupWithNavController(navController)
        navView.visibility = View.VISIBLE
        supportFragmentManager.addOnBackStackChangedListener {
            // Logika visibilitas untuk seluruh fragment
            val visibleFragments = supportFragmentManager.fragments
            Log.d("DashboardActivity", "Visible Fragments: $visibleFragments")

            val isDetailDogFragmentVisible = visibleFragments
                .any { it is DetailDogFragment && it.isVisible}

            val isDetailMapsFragmentVisible = visibleFragments
                .any { it is MapsDetailFragment && it.isVisible }
            if (isDetailDogFragmentVisible || isDetailMapsFragmentVisible) {
                Log.d("DashboardActivity", "Hiding navView")
                navView.visibility = View.GONE
            } else {
                Log.d("DashboardActivity", "Showing navView")
                navView.visibility = View.VISIBLE
            }
        }
    }
}