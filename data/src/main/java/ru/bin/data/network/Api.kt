package ru.bin.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.bin.data.model.BinInformation

interface Api {

    @GET("{binNumber}")
    suspend fun getBankInformationByBin(@Path("binNumber") binNumber: String): BinInformation

}