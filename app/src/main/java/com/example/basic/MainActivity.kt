package com.example.basic

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basic.SBQuery.Companion.supabase
import com.example.basic.adapters.MainAdapter
import com.example.basic.databinding.ActivityMainBinding
import com.example.basic.dataclass.VideoDataClassItem
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var videoList: MutableList<VideoDataClassItem>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            fetchVideo()
        }
    }

    private suspend fun fetchVideo() {
        try {
            withContext(Dispatchers.IO) {
                val result = supabase.from("video").select().decodeList<VideoDataClassItem>()
                Log.e("MainActivity", "Video Title: ${result[0].dateAdded} ")
                videoList?.addAll(result)
            }
            updateNoteList()
        } catch (e: Exception) {
            // Handle the exception
            Log.e("MainActivity", "Error message: ${e.message}")

        }
    }

    private fun updateNoteList() {
        val videoAdapter = MainAdapter(videoList!!)
        binding.recyclerView.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        val searchView=binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                videoAdapter.filter(newText.orEmpty())
                return true
            }
        })
    }
}
