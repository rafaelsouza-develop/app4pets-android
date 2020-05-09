package com.appforpets.app4pets.helpers



import com.appforpets.app4pets.App4petsApplication.Companion.context
import com.appforpets.app4pets.data.local.CredentialsDaoImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitHelper {
    //private var retrofit: Retrofit? = null
    private var usingAuth = false
    private val credentialsDao = CredentialsDaoImpl(context)


        const val BASE_URL = "https://app4pets-backend.herokuapp.com/"


    private var httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()

                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun getRetrofit(): Retrofit {

        return retrofit!! //this can be forced because it will be created on rebuildRetrofit if was null
    }


}