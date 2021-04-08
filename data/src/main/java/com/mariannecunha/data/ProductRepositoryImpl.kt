package com.mariannecunha.data

import com.mariannecunha.data.remote.ProductService
import com.mariannecunha.domain.model.ProductsWrapper
import com.mariannecunha.domain.repository.ProductRepository
import io.reactivex.Observable

class ProductRepositoryImpl(private val service: ProductService) : ProductRepository {

    override fun getProducts(): Observable<ProductsWrapper> = service.getProducts()
}
