package com.decadev.westwing.retrofits

import com.decadev.westwing.DataModel.Campaigns
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GetCampaignData {
    @GET("campaigns.json")
    fun get(): Observable<Campaigns>

//    @GET("campaigns.json")
//    fun getDetails(@Path("id")id: Int): Observable<Campaigns>
}