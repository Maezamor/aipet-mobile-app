package com.capstone.aipet.ui.history


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.databinding.FragmentHistoryBinding
import com.capstone.aipet.pref.UserPreference
import com.capstone.aipet.ui.adapter.ItemHistoryAdapter

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
        viewModel.getHistoryList(userId ?: 0)

    }
    private fun setupRecyclerView() {
        historyAdapter = ItemHistoryAdapter {
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
                    showLoading(true)
                }
                is DataResult.Success -> {
                    // Sembunyikan indikator loading dan tampilkan data
                    if (result.data == null || result.data.isEmpty()) {
                        showToast("Oops sorry you haven't adopted it yet")
                        binding.rvListHistory.visibility = View.GONE
                        showLoading(false)
                    } else {
                        binding.rvListHistory.visibility = View.VISIBLE
                        historyAdapter.submitList(result.data)
                        showLoading(false)
                    }
                }

                else -> {showLoading(false)}
            }
        }
    }
    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}