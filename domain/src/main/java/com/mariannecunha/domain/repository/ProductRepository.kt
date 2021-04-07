package com.mariannecunha.domain.repository

import com.mariannecunha.domain.model.GlobalProducts
import io.reactivex.Observable

interface ProductRepository {
    fun getProducts(): Observable<GlobalProducts>
//    fun clearAllProducts(): Observable<GlobalProducts>
}
