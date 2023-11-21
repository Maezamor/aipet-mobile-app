package com.capstone.aipet.ui.home



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.aipet.R
import com.capstone.aipet.data.DummyDataDog
import com.capstone.aipet.databinding.FragmentHomeBinding
import com.capstone.aipet.ui.adapter.ItemDogAdapter
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemDogAdapter
    private val viewModel: HomeViewModel by viewModels()
    private val listDog = ArrayList<DummyDataDog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = ItemDogAdapter(listDog)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listDog.addAll(getListDog())
        showRecyclerView()


    }
    private fun getListDog(): ArrayList<DummyDataDog> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataBreed = resources.getStringArray(R.array.data_breed)
        val dataAge = resources.getStringArray(R.array.data_age)
        val dataGender = resources.getStringArray(R.array.data_gender)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDog = ArrayList<DummyDataDog>()

        for (i in dataName.indices) {
            val Dog =
                DummyDataDog(dataName[i], dataBreed[i], dataAge[i], dataGender[i], dataPhoto.getResourceId(i, -1))
            listDog.add(Dog)
        }
        return listDog
    }
    private fun showRecyclerView() {
        binding.rvListdog.setHasFixedSize(true)
        binding.rvListdog.adapter = adapter
        binding.rvListdog.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvListdog.viewTreeObserver
    }







}