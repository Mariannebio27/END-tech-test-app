package com.mariannecunha.data

import com.mariannecunha.data.search.ProductService
import com.mariannecunha.domain.model.GlobalProducts
import com.mariannecunha.domain.repository.ProductRepository
import io.reactivex.Observable

class ProductRepositoryImpl(private val service: ProductService) : ProductRepository {

    override fun getProducts(): Observable<GlobalProducts> {

        return service.getProducts()
    }
}
