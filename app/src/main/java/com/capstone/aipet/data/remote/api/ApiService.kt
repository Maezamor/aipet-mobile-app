package com.capstone.aipet.data.remote.api

import com.capstone.aipet.data.filterData.sterilization.Sterilization
import com.capstone.aipet.data.remote.response.checkout.CheckoutResponse
import com.capstone.aipet.data.remote.response.dogs.DetailDogResponse
import com.capstone.aipet.data.remote.response.dogs.ResponseDogs
import com.capstone.aipet.data.remote.response.dogs.TestResponse
import com.capstone.aipet.data.remote.response.history.HistoryResponse
import com.capstone.aipet.data.remote.response.login.ResponseLogin
import com.capstone.aipet.data.remote.response.maps.ResponseShelter
import com.capstone.aipet.data.remote.response.onboarding.ResponseOnboarding
import com.capstone.aipet.data.remote.response.register.ResponseRegister
import com.capstone.aipet.data.remote.response.rescue.ResponseRescue
import com.capstone.aipet.data.remote.response.service.ServiceResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("users")
    suspend fun register(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseRegister

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseLogin

    @GET("dogs/filter")
    suspend fun getDogs(
        @Query("page") page: Int,
        @Query("limit") size: Int,
    ) : TestResponse

    @GET("service/list")
    suspend fun getServices(
        @Query("page") page: Int,
        @Query("limit") size: Int,
    ): ServiceResponse

    @GET("dogs")
    suspend fun getDetailDogs(
        @Query("id") id: Int,
    ):DetailDogResponse

    @GET("adoption/create")
    suspend fun checkoutDogs(
        @Query("dog_id") id: Int
    ): CheckoutResponse

    @GET("adoption/get")
    suspend fun  historyAdoption(
        @Query("user_id") id: Int
    ): HistoryResponse

    @FormUrlEncoded
    @POST("onboarding/1/start")
    suspend fun postOnboarding1(
        @Field("group") group: String,
        @Field("sterlisasi") sterilization: String,
        @Field("age") age: String,
    ): ResponseOnboarding

    @FormUrlEncoded
    @POST("onboarding/2/start")
    suspend fun postOnboarding2(
        @Field("rescueStory1") rescueStory1: String,
        @Field("rescueStory2") rescueStory2: String,
        @Field("rescueStory3") rescueStory3: String,
    ): ResponseOnboarding
    @FormUrlEncoded
    @POST("onboarding/2/start")
    suspend fun postRequestStory(
        @Field("requestStory") requestStory: String,
    ): ResponseOnboarding

    @GET("dogs/rescue")
    suspend fun getRescue():ResponseRescue

    @GET("selter/coordinat/list")
    suspend fun getListShelter():ResponseShelter
}