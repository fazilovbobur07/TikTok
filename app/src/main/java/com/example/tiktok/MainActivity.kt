package com.example.tiktok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiktok.DB.MyDBHelper
import com.example.tiktok.adapters.MyViewPagerAdapter
import com.example.tiktok.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var myDBHelper: MyDBHelper
    lateinit var myPageAdpter:MyViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.addButton.setOnClickListener{
            val intent = Intent(this,ViewActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        myDBHelper = MyDBHelper(this)
        myPageAdpter = MyViewPagerAdapter(myDBHelper.getAllReels())
        binding.viewPager.adapter = myPageAdpter
    }

}