package com.example.lntdemo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lntdemo.R
import com.example.lntdemo.data.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ListItemViewModel @Inject constructor() : ViewModel() {

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

    private val _filteredList = MutableLiveData<List<ListItem>>(_allItems)
    val filteredList: LiveData<List<ListItem>> get() = _filteredList

    fun filterList(query: String) {
        _filteredList.value = if (query.isEmpty()) _allItems
        else _allItems.filter { it.label.contains(query, ignoreCase = true) }
    }
}
