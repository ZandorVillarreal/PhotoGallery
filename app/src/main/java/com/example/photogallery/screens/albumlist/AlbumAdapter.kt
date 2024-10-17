package com.example.photogallery.screens.albumlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.photogallery.databinding.ItemMediaBinding
import com.example.photogallery.models.Image
import com.example.photogallery.models.Media
import com.example.photogallery.models.Video


class AlbumAdapter(
    private val albums: LinkedHashMap<String, ArrayList<Media>>,
    private val onAlbumClick: ((String) -> Unit)? = null
):
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(val binding: ItemMediaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemMediaBinding.inflate(inflater, viewGroup, false)

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val thumbnail = ArrayList(albums.values)[position][0]
        val name = ArrayList(albums.keys)[position]

        if (thumbnail is Image) {
            holder.binding.image.load(thumbnail.uri) {
                crossfade(true)
            }
        } else if (thumbnail is Video) {
            holder.binding.image.load(thumbnail.thumbnail) {
                crossfade(true)
            }
        }
        holder.binding.name.text = name

        holder.binding.image.setOnClickListener {
            onAlbumClick?.invoke(name)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}