package com.example.besinkitabi.services

import com.example.besinkitabi.model.Besin
import retrofit2.http.GET

interface BesinAPI {

    // base url -> https://raw.githubusercontent.com/
    // end point -> atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json")
    suspend fun getBesin() : List<Besin>

}