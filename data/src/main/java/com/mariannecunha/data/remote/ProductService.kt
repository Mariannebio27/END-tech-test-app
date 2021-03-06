package com.mariannecunha.data.remote

import com.mariannecunha.domain.model.ProductsWrapper
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ProductService {
    @GET("android_test_example.json")
    fun getProducts(): Observable<ProductsWrapper>
}
