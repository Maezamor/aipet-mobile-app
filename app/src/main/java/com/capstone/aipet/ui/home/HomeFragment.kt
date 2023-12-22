package com.capstone.aipet.ui.home



import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.databinding.FragmentHomeBinding
import com.capstone.aipet.pref.UserPreference
import com.capstone.aipet.ui.adapter.ItemDogAdapter
import com.capstone.aipet.ui.adapter.ItemMatchDogAdapter
import com.capstone.aipet.ui.home.detaildog.DetailDogFragment
import com.capstone.aipet.ui.login.LoginActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter1: ItemDogAdapter
    private lateinit var adapter2: ItemMatchDogAdapter
    private val viewModel: HomeViewModel by viewModels{
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showRecyclerView()
        onBackPressed()
        binding.HomeAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_logout -> {
                    UserPreference.logOut(requireContext())
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    requireActivity().finish()
                    true
                }
                else -> false
            }
        }

    }

    private fun showRecyclerView() {
        adapter1  = ItemDogAdapter { dogs ->
            val detailDogFragment = DetailDogFragment()

            val bundle = Bundle()
            bundle.putString(DetailDogFragment.EXTRA_NAME, dogs.name)
            bundle.putInt(DetailDogFragment.EXTRA_ID, dogs.id)
            bundle.putString(DetailDogFragment.EXTRA_AVATAR, dogs.picture)
            bundle.putString(DetailDogFragment.EXTRA_STORY, dogs.rescueStory)
            bundle.putString(DetailDogFragment.EXTRA_AGE, dogs.age)
            detailDogFragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_activity_dashboard, detailDogFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        adapter2  = ItemMatchDogAdapter { dogs ->
            val detailDogFragment = DetailDogFragment()

            val bundle = Bundle()
            bundle.putString(DetailDogFragment.EXTRA_NAME, dogs.name)
            bundle.putInt(DetailDogFragment.EXTRA_ID, dogs.id!!)
            bundle.putString(DetailDogFragment.EXTRA_AVATAR, dogs.picture)
            bundle.putString(DetailDogFragment.EXTRA_STORY, dogs.rescueStory)
            bundle.putString(DetailDogFragment.EXTRA_AGE, dogs.age)
            detailDogFragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_activity_dashboard, detailDogFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        if (requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvListdog.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.rvListMatchDog.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        } else {
            binding.rvListMatchDog.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvListdog.layoutManager = GridLayoutManager(requireContext(), 2)
        }
        binding.rvListdog.adapter = adapter1
        binding.rvListMatchDog.adapter = adapter2
        binding.rvListdog.setHasFixedSize(false)
        viewModel.dogs.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                Log.d("HomeFragment", "Data dari API: $data")
                showLoading(true)
                adapter1.submitData(viewLifecycleOwner.lifecycle, data)
                Log.d("homefragment","data anjing masuk")
            }else{
                Log.d("HomeFragment", "Data anjing kosong atau null.")
            }
            showLoading(false)
        }
        viewModel.historyList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataResult.Loading -> {
                    // Tampilkan indikator loading
                }

                is DataResult.Success -> {
                    // Sembunyikan indikator loading dan tampilkan data
                    adapter2.submitList(result.data)
                }
                else -> {}
            }
        }
        viewModel.getRecomendList()
        adapter1.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter1.retry()
            }
        )



        adapter1.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter1.itemCount == 0
            if (isListEmpty) {
                binding.errorMsg.visibility = View.VISIBLE
            } else {
                binding.errorMsg.visibility = View.GONE
            }
            binding.errorMsg.visibility = if (loadState.refresh is LoadState.Error) View.VISIBLE else View.GONE
            binding.retryButton.visibility = if (loadState.refresh is LoadState.Error){
                View.VISIBLE
                Log.e("HomeFragment", "Error loading data: ${loadState.refresh}")}else{
                View.GONE
            }
            binding.retryButton.setOnClickListener {
                adapter1.retry()
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
    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }







}