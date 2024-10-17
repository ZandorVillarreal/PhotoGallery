package com.example.photogallery.models

import android.net.Uri

data class Image(override val uri: Uri, override val name: String): Media(uri, name)
