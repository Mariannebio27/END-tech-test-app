package com.mariannecunha.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariannecunha.domain.model.Product
import com.mariannecunha.presentation.databinding.MenswearListItemBinding

class MenswearAdapter() : RecyclerView.Adapter<MenswearAdapter.MenswearViewHolder>() {

    private val products = mutableListOf<Product>()

    fun updateProducts(products: MutableList<Product>) {

        if (this.products.isNotEmpty()) {
            this.products.clear()
        }
        val sortedProducts = products.sortedByDescending { it.id }

        this.products.addAll(sortedProducts ?: listOf())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenswearViewHolder {
        val binding =
            MenswearListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MenswearViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: MenswearViewHolder, position: Int) {
        holder.itemBind(products[position])
    }

    class MenswearViewHolder(private val binding: MenswearListItemBinding) : RecyclerView.ViewHolder(binding.root) {

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
