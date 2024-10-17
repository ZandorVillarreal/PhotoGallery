package com.example.photogallery.screens.medialist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.photogallery.databinding.ItemMediaBinding
import com.example.photogallery.models.Image
import com.example.photogallery.models.Media
import com.example.photogallery.models.Video

class MediaAdapter(
    private val medias: ArrayList<Media>,
    private val onImageClick: ((Int) -> Unit)? = null,
    private val onVideoClick: ((Int) -> Unit)? = null
):
    RecyclerView.Adapter<MediaAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ItemMediaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemMediaBinding.inflate(inflater, viewGroup, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = medias[position]

        if (item is Image) {
            holder.binding.image.load(item.uri) {
                crossfade(true)
            }
        } else if (item is Video) {
            holder.binding.image.load(item.thumbnail)
        }
        holder.binding.name.text = item.name

        holder.binding.image.setOnClickListener {
            var correctPosition = position
            if (item is Image) {
                for (i in 0..position) {
                    if (medias[i] is Video) {
                        correctPosition--
                    }
                }
                onImageClick?.invoke(correctPosition)
            } else {
                for (i in 0..position) {
                    if (medias[i] is Image) {
                        correctPosition--
                    }
                }
                onVideoClick?.invoke(correctPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return medias.size
    }
}