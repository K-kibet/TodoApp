package com.example.todoapp

import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTodos = findViewById<RecyclerView>(R.id.rvTodos)
        val buttonAddTodo = findViewById<MaterialButton>(R.id.buttonAddTodo)
        val addFab = findViewById<FloatingActionButton>(R.id.add_fab)
        val inputLayout = findViewById<LinearLayout>(R.id.inputLayout)
        val etTodo = findViewById<EditText>(R.id.etTodo)

        var todoList = ArrayList<Todo>()

        val adapter = TodoAdapter(todoList)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)

        addFab.setOnClickListener {
            addFab.hide()
            inputLayout.isVisible = true
        }

        buttonAddTodo.setOnClickListener {
            addItem()
        }

        fun addItem (): Boolean{
            if(etTodo.text.equals("")) {
                return false
            }

            val title = etTodo.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size -1)


            etTodo.text.clear()
            addFab.show()
            //inputLayout.isVisible = false
            //getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
            return true
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}