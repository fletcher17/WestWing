package com.decadev.westwing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.decadev.westwing.DataModel.Image
import com.decadev.westwing.databinding.ActivityDetailsBinding
import com.decadev.westwing.retrofits.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var position = intent.extras?.getInt("pos")
        compositeDisposable.add(Retrofit.apiService.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.imageTitleText.text = it.metadata.data[position!!].name
                binding.imageDescriptonText.text = it.metadata.data[position!!].description

                Glide.with(this).load(it.metadata.data[position!!].image.url).into(binding.westwingImageDetails)
            })





    }
}