package com.qersh.swimmig_schole_api

import com.qersh.swimmig_schole_api.model.note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JsonPlaceHolderApi {
    @POST("coaches")
    fun sendurldata(
        @Body note: note
    ): Call<note>
}