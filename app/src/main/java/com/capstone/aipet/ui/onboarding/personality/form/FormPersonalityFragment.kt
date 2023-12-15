package com.capstone.aipet.ui.onboarding.personality.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.capstone.aipet.DashboardActivity
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.customview.button.ButtonOnBoarding
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.databinding.FragmentFormPersonalityBinding

class FormPersonalityFragment : Fragment() {

    private lateinit var _binding: FragmentFormPersonalityBinding
    private val binding get() = _binding!!
    private var requestStory: String = ""
    private lateinit var  buttonOnBoarding: ButtonOnBoarding
    private val viewModel: FormPersonalityViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormPersonalityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonOnBoarding = binding.btnOnboarding
//        onTestClick()
        intentToDashboard()
        buttonSubmitEnable()
    }
//    private fun onTestClick() {
//        binding.btnTest.setOnClickListener {
//            val intent = Intent(
//                requireActivity(),
//                DashboardActivity::class.java
//            )
//            requireActivity().startActivity(intent)
//        }
//    }
    private fun setButtonSubmitEnable(){
        requestStory = binding.editRescue.text.toString()
        buttonOnBoarding.isEnabled = !requestStory.isNullOrEmpty()

    }
    private fun buttonSubmitEnable(){
        buttonOnBoarding.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setButtonSubmitEnable()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })
    }
    private fun intentToDashboard(){
        requestStory = binding.editRescue.text.toString()
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
        buttonOnBoarding.setOnClickListener{
            viewModel.postRequestStory(requestStory ).observe(viewLifecycleOwner){
                if (it != null) {
                    when (it) {
                        is DataResult.Loading -> {
                            showLoading(true)
                        }
                        is DataResult.Success -> {
                            showLoading(false)
                            it.data
                            val intent = Intent(requireContext(), DashboardActivity::class.java)
                            startActivity(intent)
                        }

                        is DataResult.Error -> {
                            showLoading(false)
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state
    }
}