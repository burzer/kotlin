package com.example.coinscore2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coinscore2.databinding.ActivityMainBinding
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.common.AdRequest


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var counter = 0
    var pref : SharedPreferences? = null

    lateinit var tvResult: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.banner.setAdUnitId("R-M-9546722-1")
        binding.banner.setAdSize(AdSize.stickySize(300))
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
        
        
        
        tvResult = findViewById(R.id.tvResult)

        pref = getSharedPreferences("TABLE", MODE_PRIVATE)

        counter = pref?.getInt("counter", counter)!!
        tvResult.text = counter.toString()


    }
    fun onClickAdd(view : View)
    {
       counter++

        tvResult.text = counter.toString()

    }
    fun saveData(res: Int){
        val editor = pref?.edit()


        editor?.putInt("counter", res)
        editor?.apply()
    }

    override fun onStop() {
        super.onStop()
        saveData(counter)

    }

    override fun onResume() {
        super.onResume()
        saveData(counter)

    }


    override fun onDestroy() {
        super.onDestroy()
        saveData(counter)
    }




    }
