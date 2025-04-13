package com.example.lntdemo.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.lntdemo.model.MainViewModel
import com.example.lntdemo.R
import com.example.lntdemo.adapter.ListItemAdapter
import com.example.lntdemo.model.ListItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListItemActivity : AppCompatActivity() {

    private val viewModel: ListItemViewModel by viewModels()
    private lateinit var adapter: ListItemAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewPager: ViewPager2
    private lateinit var fab: FloatingActionButton
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)


        // Initialize Views
        recyclerView = findViewById(R.id.recyclerView)
        viewPager = findViewById(R.id.viewPager)
        fab = findViewById(R.id.fab)
        searchEditText = findViewById(R.id.searchEditText)

        // Setup RecyclerView
        adapter = ListItemAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Setup ViewPager2 Adapter
        viewPager.adapter = object : RecyclerView.Adapter<ImageViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
                val imageView = ImageView(parent.context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                return ImageViewHolder(imageView)
            }

            override fun getItemCount() = viewModel.images.size

            override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
                holder.imageView.setImageResource(viewModel.images[position])
            }
        }

        // Observe LiveData from ViewModel
        viewModel.filteredList.observe(this) { list ->
            adapter.submitList(list)
        }

        // Search input listener
        searchEditText.addTextChangedListener {
            viewModel.filterList(it?.toString().orEmpty())
        }

        // FAB click to show bottom sheet
        fab.setOnClickListener {
            val currentList = viewModel.filteredList.value ?: emptyList()
            StatsBottomSheetDialog(currentList).show(supportFragmentManager, "stats")
        }
    }

    inner class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}