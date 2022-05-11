package de.hicedevelopments.princemusicapp.common

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import de.hicedevelopments.princemusicapp.BuildConfig

private const val GLIDE_DISK_CACHE_SIZE_BYTES = 10 * 1024 * 1024

@GlideModule
class GlideConfigModule : AppGlideModule() {
    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) {
        if (BuildConfig.DEBUG) {
            //external cache location is easier to debug
            builder.setDiskCache(
                ExternalPreferredCacheDiskCacheFactory(
                    context,
                    GLIDE_DISK_CACHE_SIZE_BYTES.toLong()
                )
            )
        } else {
            builder.setDiskCache(
                InternalCacheDiskCacheFactory(
                    context,
                    GLIDE_DISK_CACHE_SIZE_BYTES.toLong()
                )
            )
        }

        val calculator = MemorySizeCalculator.Builder(context).build()
        val defaultMemoryCacheSize = calculator.memoryCacheSize
        val defaultBitmapPoolSize = calculator.bitmapPoolSize
        Log.d(
            "PrinceMusicApp",
            "Setting up Glide cache with params: discCache=$GLIDE_DISK_CACHE_SIZE_BYTES, memoryCache=$defaultMemoryCacheSize, bitmapPool=$defaultBitmapPoolSize"
        )
        builder.setMemoryCache(LruResourceCache(defaultMemoryCacheSize.toLong()))
        builder.setBitmapPool(LruBitmapPool(defaultBitmapPoolSize.toLong()))

        val requestOptions = defaultRequestOptions()
        builder.setDefaultRequestOptions(requestOptions)
    }

    override fun isManifestParsingEnabled() = false

    private fun defaultRequestOptions() = RequestOptions()
        .format(DecodeFormat.DEFAULT)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .dontAnimate()
}