package com.example.photoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapp.databinding.ActivityMainBinding
import com.example.photoapp.model.ImageModel
import com.example.photoapp.model.PixabayModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var imageAdapter = ImageAdapter(arrayListOf())
    lateinit var list: ArrayList<ImageModel>
    var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        binding.btnSearch.setOnClickListener {
            doRequest(page)
        }
        binding.Exchange.setOnClickListener {
            doRequest(++page)
        }

    }

    private fun doRequest(page:Int){
        App.api.getImageByWord(keyWord = binding.edSearch.text.toString().trim(), page = page)
            .enqueue(object: Callback<PixabayModel>{
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {

                    response.body()?.hits?.let{
                        list = it as ArrayList<ImageModel>
                    }
                    if (page-1 != 0){
                        imageAdapter.addItems(list)
                    }else{
                        imageAdapter= ImageAdapter(list)
                        binding.recyclerView.adapter = imageAdapter
                    }


                    Log.e("WWTTFF","onResponse: ${response.body()?.hits!![0].largeImageURL}")

                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("WWTTFF","onFailure: ${t.message}")
                }
            })
    }
}