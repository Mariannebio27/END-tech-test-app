package com.mariannecunha.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariannecunha.domain.model.Product
import com.mariannecunha.presentation.databinding.ProductListItemBinding

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val products = mutableListOf<Product>()

    fun updateProducts(products: List<Product>) {

        if (this.products.isNotEmpty()) {
            this.products.clear()
        }

        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProductViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemBind(products[position])
    }

    class ProductViewHolder(private val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun itemBind(product: Product) = with(binding) {

            productNameTextView.text = product.name
            productPriceTextView.text = product.price

            setUpImageView(product)
        }

        private fun setUpImageView(product: Product) = with(binding.productImageView) {
            Glide.with(context)
                .load(product.image)
                .into(this)
        }
    }
}
