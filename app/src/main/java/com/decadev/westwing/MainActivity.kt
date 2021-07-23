package com.decadev.westwing

import android.content.Intent
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decadev.westwing.Adapter.WestWingAdapter
import com.decadev.westwing.clickListener.ImageClickListener
import com.decadev.westwing.databinding.ActivityMainBinding
import com.decadev.westwing.retrofits.CommonUse
import com.decadev.westwing.retrofits.Retrofit
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), ImageClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var westWingRecyclerView: RecyclerView
    lateinit var westWingAdapter : WestWingAdapter
    lateinit var disposable: Disposable


    private var compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        westWingRecyclerView = binding.recyclerview1

        //check for internet connectivity
        disposable = ReactiveNetwork
            .observeNetworkConnectivity(this)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.state() == NetworkInfo.State.CONNECTED) {
                    compositeDisposable.add(Retrofit.apiService.get()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            for (item in it.metadata.data) {
                                if(item.name != null) {
                                    CommonUse.newCampaignData.add(item)
                                    westWingAdapter = WestWingAdapter(this, CommonUse.newCampaignData, this)
                                    westWingRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                                    westWingRecyclerView.adapter = westWingAdapter
                                }
                            }

                        })
                } else if (it.state() == NetworkInfo.State.DISCONNECTED) {
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
                }
            }


    }


    override fun imageClick(position: Int) {
        disposable = ReactiveNetwork
            .observeNetworkConnectivity(this)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.state() == NetworkInfo.State.CONNECTED) {
                    val intent = Intent(this, DetailsActivity::class.java)
                    intent.putExtra("pos", position)
                    startActivity(intent)
                } else if (it.state() == NetworkInfo.State.DISCONNECTED) {
                    Snackbar.make(binding.root, "No Internet", Snackbar.LENGTH_INDEFINITE).show()
                }
            }

    }
}