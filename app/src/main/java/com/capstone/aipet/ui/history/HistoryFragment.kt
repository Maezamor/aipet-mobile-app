package com.capstone.aipet.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.databinding.FragmentHistoryBinding
import com.capstone.aipet.databinding.FragmentHomeBinding
import com.capstone.aipet.pref.UserPreference
import com.capstone.aipet.ui.adapter.ItemHistoryAdapter
import com.capstone.aipet.ui.home.detaildog.DetailDogViewModel

class HistoryFragment : Fragment() {


    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: ItemHistoryAdapter
    private val viewModel: HistoryViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        observeHistoryList()
        val sharedPreferences = UserPreference.init(requireContext(), "onSignIn")
        val userId = sharedPreferences.getInt("id", 0)
//        viewModel.getHistoryById(userId ?: 0)
        viewModel.getHistoryList(userId ?: 0)

    }
    private fun setupRecyclerView() {
        historyAdapter = ItemHistoryAdapter { selectedHistory ->
            // Implementasikan logika ketika item history di klik
            showToast("History di klik: ${selectedHistory.name}")
        }

        binding.rvListHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

    private fun observeHistoryList() {
        viewModel.historyList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataResult.Loading -> {
                    // Tampilkan indikator loading
                }
                is DataResult.Success -> {
                    // Sembunyikan indikator loading dan tampilkan data
                    historyAdapter.submitList(result.data)
                }
                is DataResult.Error -> {
                    // Sembunyikan indikator loading dan tampilkan pesan error
                    //Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                    showToast("error from ${result.error}")
                }
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}