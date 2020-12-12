package com.example.viewmodel.retrofit

import com.example.viewmodel.common.Constans
import okhttp3.Interceptor
import okhttp3.Response

class TheMoviesDBInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
                //añadimos parametros en la url de la cadena que recibimos (chain)
        val  urlWithParams=chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constans.URL_PARAM_API_KEY, Constans.API_KEY)
            .addQueryParameter(Constans.URL_PARAM_LANGUAJE, "es-ES")
            .build()
        var request=chain.request()
        request=request?.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type","application/json")
            .addHeader("Accept","application/json")
            .build()
        return chain.proceed(request)

    }
}