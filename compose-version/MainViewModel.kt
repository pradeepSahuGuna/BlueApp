package com.example.lntdemo.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lntdemo.R
import com.example.lntdemo.data.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    val images = listOf(
        R.drawable.placeholder1,
        R.drawable.placeholder2,
        R.drawable.placeholder3
    )

    private val _allItems = listOf(
        ListItem("Apple", R.drawable.placeholder1),
        ListItem("Banana", R.drawable.placeholder2),
        ListItem("Orange", R.drawable.placeholder3),
        ListItem("Blueberry", R.drawable.placeholder1),
        ListItem("Pineapple", R.drawable.placeholder2),
        ListItem("Strawberry", R.drawable.placeholder3),
        ListItem("Watermelon", R.drawable.placeholder1)
    )

    var filteredList by mutableStateOf(_allItems)
        private set

    fun filterList(query: String) {
        filteredList = if (query.isEmpty()) _allItems
        else _allItems.filter { it.label.contains(query, ignoreCase = true) }
    }

}
