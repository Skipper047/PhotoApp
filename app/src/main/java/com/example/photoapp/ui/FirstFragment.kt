package com.example.photoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapp.App
import com.example.photoapp.ImageAdapter
import com.example.photoapp.R
import com.example.photoapp.databinding.FragmentFirstBinding
import com.example.photoapp.model.ImageModel
import com.example.photoapp.model.PixabayModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    var imageAdapter = ImageAdapter(arrayListOf())
    lateinit var list: ArrayList<ImageModel>
    var page: Int = 1
    var scroll: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicker()
    }

    private fun initClicker() {
        binding.btnSearch.setOnClickListener {
            doRequest(page)
            scroll = false
        }
        binding.Exchange.setOnClickListener {
            doRequest(++page)
            scroll=false
        }
        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                scroll = true
                doRequest(++page)
            }
        })

    }

    private fun doRequest(page:Int){
        App.api.getImageByWord(keyWord = binding.edSearch.text.toString().trim(), page = page)
            .enqueue(object: Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {

                    response.body()?.hits?.let{
                        list = it as ArrayList<ImageModel>
                    }
                    if (scroll && page-1 !=0 && page > page-1){
                        imageAdapter.addItems(list)
                    }else if (page-1 != 0){
                        imageAdapter.addItems(list)
                    }else {
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