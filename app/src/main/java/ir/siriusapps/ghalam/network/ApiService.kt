package ir.siriusapps.ghalam.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiService(gson: Gson) {

    private val retrofit: Retrofit
    private val client: OkHttpClient
    private val apis: Apis

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(10, TimeUnit.SECONDS)
        clientBuilder.readTimeout(10, TimeUnit.SECONDS)
        client = clientBuilder.addInterceptor(loggingInterceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:4000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()

        apis = retrofit.create(Apis::class.java)
    }

    public fun getApis(): Apis = apis

}