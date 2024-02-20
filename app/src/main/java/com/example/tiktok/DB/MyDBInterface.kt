package com.example.tiktok.DB

import com.example.tiktok.models.MyReel

interface MyDBInterface {
    fun addReels(MyReel: MyReel)
    fun getAllReels():ArrayList<MyReel>
}