package com.example.lntdemo.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lntdemo.R
import com.example.lntdemo.data.ListItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StatsBottomSheetDialog(private val items: List<ListItem>) : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.dialog_stats, container, false)
        val textView = view.findViewById<TextView>(R.id.txt_statsTextView)

        val charMap = mutableMapOf<Char, Int>()
        items.forEach { item ->
            item.label.forEach {
                if (it.isLetter()) charMap[it] = charMap.getOrDefault(it, 0) + 1
            }
        }

        val topChars = charMap.entries.sortedByDescending { it.value }.take(3)
        textView.text = buildString {
            append("Total Items: ${items.size}\nTop Characters:\n")
            topChars.forEach { append("${it.key} = ${it.value}\n") }
        }

        return view
    }
}
