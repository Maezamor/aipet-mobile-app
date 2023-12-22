package com.capstone.aipet.data.remote.response.dogs

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "dogs")
@Parcelize
data class ItemDogs(
        @field:SerializedName("character")
        val character: String,
        @field:SerializedName("gender")
        val gender: String,

        @field:SerializedName("updated_at")
        val updatedAt: String,
        @field:SerializedName("type")
        val type: String,
        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("rescue_story")
        val rescueStory: String,

        @field:SerializedName("created_at")
        val createdAt: String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("age")
        val age: String,

        @field:SerializedName("picture")
        val picture: String,

    ):Parcelable
