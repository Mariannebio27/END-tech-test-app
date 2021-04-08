package com.mariannecunha.data.remote

import com.mariannecunha.domain.model.GlobalProducts
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductService {
    @GET("android_test_example.json")
    fun getProducts(): Observable<GlobalProducts>
}
