package com.example.viewModel.retrofit

import com.example.viewModel.common.Constans
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private  val theMovieServices:TheMovieServices
    private val retrofit: Retrofit

    companion object{
        var instance:TheMovieDBClient?=null

                 get() {
                   if(field==null){
                       instance= TheMovieDBClient()
                   }
                     return field
                 }
    }
    init {
        //incluir el interceptor que hemos definido
        var okHttpClientBuilder=OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMoviesDBInterceptor())

        val cliente = okHttpClientBuilder.build()
        //construimos el cliente de retrofit
        retrofit=Retrofit.Builder()
            .baseUrl(Constans.TMDBAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()
            //Iniciamos el servicio de retrofit a partir del objeto retrofit
        theMovieServices=retrofit.create(TheMovieServices::class.java)
    }
    fun getTheMovieDBService()=theMovieServices
}