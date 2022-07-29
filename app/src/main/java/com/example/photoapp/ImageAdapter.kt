package com.example.photoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.photoapp.databinding.ItemImageBinding
import com.example.photoapp.model.ImageModel

class ImageAdapter(private val list: ArrayList<ImageModel> = arrayListOf()): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount()= list.size
    fun addItems(model: ArrayList<ImageModel>) {
        list.addAll(model)
        notifyDataSetChanged()
    }


    class ImageViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(model: ImageModel){
            binding.pixImage.load(model.largeImageURL)
        }
    }
}