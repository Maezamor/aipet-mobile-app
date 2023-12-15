package com.capstone.aipet

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.aipet.di.Injection
import com.capstone.aipet.ui.healthservice.service.PetCareViewModel
import com.capstone.aipet.ui.history.HistoryViewModel
import com.capstone.aipet.ui.home.HomeViewModel
import com.capstone.aipet.ui.home.detaildog.DetailDogViewModel
import com.capstone.aipet.ui.login.LoginViewModel
import com.capstone.aipet.ui.maps.MapsViewModel
import com.capstone.aipet.ui.onboarding.personality.filter.FilterOnBoardingViewModel
import com.capstone.aipet.ui.onboarding.personality.form.FormPersonalityViewModel
import com.capstone.aipet.ui.onboarding.personality.match.MatchmakingViewModel
import com.capstone.aipet.ui.register.RegisterViewModel

class ViewModelFactory(private val context: FragmentActivity) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(Injection.provideUserRepository(context)) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideUserRepository(context)) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(Injection.provideDogsRepository(context)) as T
            }
            modelClass.isAssignableFrom(PetCareViewModel::class.java) -> {
                PetCareViewModel(Injection.provideServicesRepository(context)) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(Injection.provideDogsRepository(context)) as T
            }
            modelClass.isAssignableFrom(DetailDogViewModel::class.java) -> {
                DetailDogViewModel(Injection.provideDogsRepository(context)) as T
            }
            modelClass.isAssignableFrom(FilterOnBoardingViewModel::class.java) -> {
                FilterOnBoardingViewModel(Injection.provideDogsRepository(context)) as T
            }
            modelClass.isAssignableFrom(MatchmakingViewModel::class.java) -> {
                MatchmakingViewModel(Injection.provideDogsRepository(context)) as T
            }
            modelClass.isAssignableFrom(FilterOnBoardingViewModel::class.java) -> {
                FilterOnBoardingViewModel(Injection.provideDogsRepository(context)) as T
            }
            modelClass.isAssignableFrom(FormPersonalityViewModel::class.java) -> {
                FormPersonalityViewModel(Injection.provideDogsRepository(context)) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(Injection.provideSheltersRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}