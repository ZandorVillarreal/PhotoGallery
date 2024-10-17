package com.example.photogallery.screens.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.example.photogallery.R
import com.example.photogallery.databinding.FragmentAlbumListBinding
import com.example.photogallery.main.Navigator
import com.example.photogallery.media.MediaStorage
import com.example.photogallery.screens.medialist.MediaListFragment
import javax.inject.Inject

@AndroidEntryPoint
class AlbumListFragment: Fragment() {
    private lateinit var binding: FragmentAlbumListBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumListBinding.inflate(inflater, container, false)

        binding.mediaList.layoutManager = GridLayoutManager(requireContext(), 3)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mediaList.adapter = AlbumAdapter(MediaStorage.getAlbums()) {
            navigator.replaceFragment(
                R.id.container,
                MediaListFragment.newInstance(it),
                true
            )
        }
    }
}