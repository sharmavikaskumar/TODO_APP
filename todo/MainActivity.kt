package com.example.todo

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var tasklist: MutableList<Task>
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var etTask: EditText
    private lateinit var btnAdd: Button
    private lateinit var rvTasks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        etTask = findViewById<EditText>(R.id.et_task)
        btnAdd = findViewById<Button>(R.id.btn_Add)
        rvTasks = findViewById<RecyclerView>(R.id.rv_list)

        tasklist = mutableListOf()
        taskAdapter = TaskAdapter(tasklist)

        rvTasks.adapter = taskAdapter
        rvTasks.layoutManager=LinearLayoutManager(this)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnAdd.setOnClickListener {
            val taskTitle=etTask.text.toString()
            if(!TextUtils.isEmpty(taskTitle)){
                val task=Task(taskTitle,false)
                tasklist.add(task)
                taskAdapter.notifyItemInserted(tasklist.size-1)
                etTask.text.clear()

            }
        }
        }
    }
