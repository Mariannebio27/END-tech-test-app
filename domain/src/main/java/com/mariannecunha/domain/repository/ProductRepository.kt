package com.mariannecunha.domain.repository

import com.mariannecunha.domain.model.ProductsWrapper
import io.reactivex.rxjava3.core.Observable

interface ProductRepository {
    fun getProducts(): Observable<ProductsWrapper>
}
