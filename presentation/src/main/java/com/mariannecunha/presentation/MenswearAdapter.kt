package com.mariannecunha.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mariannecunha.presentation.databinding.MenswearListItemBinding

class MenswearAdapter() : RecyclerView.Adapter<MenswearAdapter.MenswearViewHolder>() {

    private val words = mutableListOf<String>()

    fun updateWords(words: MutableList<String>) {

        if (this.words.isNotEmpty()) {
            this.words.clear()
        }

        this.words.addAll(words ?: listOf())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenswearViewHolder {
        val binding =
            MenswearListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MenswearViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return words.count()
    }

    override fun onBindViewHolder(holder: MenswearViewHolder, position: Int) {
        holder.itemBind(words[position])
    }

    class MenswearViewHolder(binding: MenswearListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun itemBind(word: String) {
        }
    }
}
