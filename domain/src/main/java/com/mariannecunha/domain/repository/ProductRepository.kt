package com.mariannecunha.domain.repository

import com.mariannecunha.domain.model.ProductsWrapper
import io.reactivex.Observable

interface ProductRepository {
    fun getProducts(): Observable<ProductsWrapper>
}
