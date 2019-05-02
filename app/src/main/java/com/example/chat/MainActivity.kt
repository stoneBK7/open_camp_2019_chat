package com.example.chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        val items = arrayListOf<String>()
        items.addAll(listOf("Apple", "バナナ"))

        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)

        val editText = findViewById<TextInputEditText>(R.id.editText)

        editText.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    addListView(editText, items, listView)
                    return true
                }
                return false
            }
        })

        // 送信ボタン
        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            addListView(editText, items, listView)
        }
    }

    /**
     * リストに追加
     */
    private fun addListView(editText : TextInputEditText, items : ArrayList<String>, listView : ListView) {
        // 1文字以上入力されている場合
        if (editText.text.toString().count() > 0) {

            // リストに追加してリストビューに表示する
            items.add(editText.text.toString())
            listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
            // 最後のリストを選択する
            listView.setSelection(items.size)

            // 入力内容をクリア
            editText.text = null
        }
    }
}