package com.example.tiktok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tiktok.DB.MyDBHelper
import com.example.tiktok.databinding.ActivityViewBinding
import com.example.tiktok.models.MyReel
import java.io.File
import java.io.FileOutputStream

class ViewActivity : AppCompatActivity() {
    private val binding by lazy {ActivityViewBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val myDBHelper = MyDBHelper(this)
        binding.videoView.setOnClickListener{
            val popupMenu = PopupMenu(this,binding.videoView)
            popupMenu.inflate(R.menu.my_cg_menu)

            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_camera ->{
                        //kamera kodini yozasiz
                    }
                    R.id.menu_gallery ->{
                        getVideoContent.launch("video/*")
                    }
                }
                true
            }

            popupMenu.show()
        }
        binding.apply {
            btnSave.setOnClickListener {
                if (absolutUri != ""){
                    val MyReel = MyReel(
                        title = edtTitle.text.toString(),
                        videoLink = absolutUri
                    )
                    myDBHelper.addReels(MyReel)
                    Toast.makeText(this@ViewActivity, "saqlandi", Toast.LENGTH_SHORT).show()
                    finish()
            }else{
                    Toast.makeText(this@ViewActivity, "Video tanlang", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    var absolutUri = ""
    private val getVideoContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                binding.videoView.setVideoURI(uri)
                binding.videoView.start()
                val inputStream = contentResolver.openInputStream(uri)
                val fileName = "video_${System.currentTimeMillis()}.mp4" // Генерируем уникальное имя файла
                val file = File(filesDir, fileName)
                val outputStream = FileOutputStream(file)
                inputStream?.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                inputStream?.close()
                outputStream.close()

                absolutUri = file.absolutePath
                Log.d("TAG", "File saved at: $absolutUri")
            }
        }

}