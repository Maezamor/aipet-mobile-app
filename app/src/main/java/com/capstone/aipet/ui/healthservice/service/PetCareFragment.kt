package com.capstone.aipet.ui.healthservice.service

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.databinding.FragmentPetCareBinding
import com.capstone.aipet.ui.adapter.ItemServiceAdapter
import com.capstone.aipet.ui.healthservice.detailservice.DetailPetCareFragment
import com.capstone.aipet.ui.home.LoadingStateAdapter

class PetCareFragment : Fragment() {

    private var _binding: FragmentPetCareBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemServiceAdapter
    private val viewModel: PetCareViewModel by viewModels{
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPetCareBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.services.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                Log.d("HomeFragment", "Data dari API: $data")
                showLoading(true)
                adapter.submitData(viewLifecycleOwner.lifecycle, data)
                Log.d("homefragment","data anjing masuk")
            }else{
                Log.d("HomeFragment", "Data anjing kosong atau null.")
            }
            showLoading(false)
        }
        showRecyclerView()
    }
    private fun showRecyclerView() {

        if (requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvListdog.layoutManager = GridLayoutManager(requireContext(), 2)
        } else {
            binding.rvListdog.layoutManager = LinearLayoutManager(requireContext())
        }
        adapter = ItemServiceAdapter { services ->
            val detailPetCareFragment = DetailPetCareFragment()
            val bundle = Bundle()
            bundle.putString(DetailPetCareFragment.EXTRA_NAME, services.name)
            bundle.putInt(DetailPetCareFragment.EXTRA_ID, services.id)
            bundle.putString(DetailPetCareFragment.EXTRA_AVATAR, services.picture)
            bundle.putString(DetailPetCareFragment.EXTRA_CITY, services.city)
            bundle.putString(DetailPetCareFragment.EXTRA_DESC, services.description)
            bundle.putString(DetailPetCareFragment.EXTRA_PHONE, services.phone)
            bundle.putString(DetailPetCareFragment.EXTRA_SM1, services.sosialMedia1)
            bundle.putString(DetailPetCareFragment.EXTRA_SM2, services.sosialMedia2)
            bundle.putString(DetailPetCareFragment.EXTRA_SM3, services.sosialMedia3)
            detailPetCareFragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.frame_container_petcare, detailPetCareFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.rvListdog.adapter = adapter
        binding.rvListdog.setHasFixedSize(false)
        adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        adapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            if (isListEmpty) {
                binding.errorMsg.visibility = View.VISIBLE
            } else {
                binding.errorMsg.visibility = View.GONE
            }
            binding.errorMsg.visibility =
                if (loadState.refresh is LoadState.Error) View.VISIBLE else View.GONE
            binding.retryButton.visibility = if (loadState.refresh is LoadState.Error) {
                View.VISIBLE
                Log.e("HomeFragment", "Error loading data: ${loadState.refresh}")
            } else {
                View.GONE
            }
            binding.retryButton.setOnClickListener {
                adapter.retry()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state
    }

}