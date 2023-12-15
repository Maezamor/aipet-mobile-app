package com.capstone.aipet.ui.onboarding.personality.match

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.capstone.aipet.R
import com.capstone.aipet.ViewModelFactory
import com.capstone.aipet.data.remote.DataResult
import com.capstone.aipet.data.remote.response.rescue.ItemRescue
import com.capstone.aipet.databinding.FragmentMatchmakingBinding
import com.capstone.aipet.ui.adapter.MatchAdapter
import com.capstone.aipet.ui.onboarding.personality.form.FormPersonalityFragment
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod

class MatchmakingFragment : Fragment(), CardStackListener {


    private lateinit var _binding: FragmentMatchmakingBinding
    private lateinit var layoutManager: CardStackLayoutManager
    private val viewModel: MatchmakingViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }
    private val adapter = MatchAdapter()
    private val binding get() = _binding!!
    private val stackView: CardStackView
        get() = binding.stackView
    private var rightSwipeCount = 0
    private var swiped1: String? = null
    private var swiped2: String? = null
    private var swiped3: String? = null
    private var rescueStory: ItemRescue? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchmakingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layoutManager = CardStackLayoutManager(requireContext(), this@MatchmakingFragment).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }
        viewModel.rescueDog.observe(viewLifecycleOwner) { responseRescue ->
            rescueStory = responseRescue.listRescue
            adapter.updateData(rescueStory!!)
        }
        viewModel.getRescue()
        stackView.layoutManager = layoutManager
        stackView.adapter = adapter
        stackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
        binding.btnClose.setOnClickListener {
            swipeLeft()
        }
        binding.btnDone.setOnClickListener {
            swipeRight()
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Left) {
            viewModel.getRescue()
        } else if (direction == Direction.Right) {
            viewModel.getRescue()
            rescueStory.let {
                if (swiped1 == null) {
                    swiped1 = it!!.rescueStory
                    Log.d("ItemSwiped", swiped1!!)
                } else if (swiped2 == null) {
                    swiped2 = it!!.rescueStory
                    Log.d("ItemSwiped", swiped1!!)
                } else if (swiped3 == null) {
                    swiped3 = it!!.rescueStory
                    Log.d("ItemSwiped", swiped1!!)
                } else {
                    Log.d("ItemSwiped", "maximum choice")
                }
            }
            rightSwipeCount++
            if (rightSwipeCount >= 3) {
                getToViewModel()
            }
        }
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }
    private fun getToViewModel() {
        Log.d("checked nullabel" ,swiped1!!)
        Log.d("checked nullabel" ,swiped2!! )
        Log.d("checked nullabel" ,swiped3!! )
        viewModel.onBoarding2(swiped1!!, swiped2!!, swiped3!!)
            .observe(viewLifecycleOwner) { dataResult ->
                when (dataResult) {
                    is DataResult.Loading -> {
                        showLoading(true)
                    }

                    is DataResult.Success -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "success",
                            Toast.LENGTH_LONG
                        ).show()
                        replaceFragmentToForm()
                    }

                    is DataResult.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "error: ${dataResult.error}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }





private fun swipeLeft() {
    if (rescueStory != null) {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()

        layoutManager.setSwipeAnimationSetting(setting)
        stackView.swipe()
    }
}

    private fun swipeRight() {
     if (rescueStory != null) {
         val setting = SwipeAnimationSetting.Builder()
             .setDirection(Direction.Right)
             .setDuration(Duration.Normal.duration)
             .setInterpolator(AccelerateInterpolator())
             .build()
         layoutManager.setSwipeAnimationSetting(setting)
         stackView.swipe()
     }
    }
    private fun replaceFragmentToForm() {
        val formFragment = FormPersonalityFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, formFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun showLoading(state: Boolean){
        binding.progressBar.isVisible = state
    }
}





