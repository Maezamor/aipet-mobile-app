package com.capstone.aipet.data.dummyTest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DummyDataDog(
    val name: String,
    val breed: String,
    val age: String,
    val gender: String,
    val photo: Int,
): Parcelable
