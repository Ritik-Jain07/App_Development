package com.example.sqliteroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class DatabaseActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val intent = intent
        BUTTON_CLICKED = intent.getStringExtra(MainActivity.BUTTON_CLICKED_KEY).toString()

        initialiseRecyclerView()

    }

    private fun initialiseRecyclerView() {
        recyclerView = findViewById(R.id.employeeDetailsRecyclerView)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        if (BUTTON_CLICKED == MainActivity.SQLITE_DEMO_BTN) {
            setupListFromSQLite() //Function call to setup list from SQLite
            recyclerView.adapter = customAdapter
            customAdapter.notifyDataSetChanged()
        } else
            setupListFromRoom()  // Function call to setup list from Room

    }

    private fun setupListFromRoom() {
        val roomDatabaseBuilder = RoomDatabaseBuilder.getInstance(this)
        var employeeList: List<EmployeeDataClass>

        Executors.newSingleThreadExecutor().execute {
            // get data from Database
            employeeList = roomDatabaseBuilder.employeeDao().getAllEmployeeDetails()

            recyclerView.apply {
                // Stuff that updates the UI
                runOnUiThread {
                    customAdapter = CustomAdapter(
                        this@DatabaseActivity,
                        employeeList as ArrayList<EmployeeDataClass>
                    )
                    recyclerView.adapter = customAdapter
                    customAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setupListFromSQLite() {
        val databaseManager = SQLiteDatabaseManager(this)

        // get data from Database
        val employeeList = databaseManager.getAllEmpDataFromSQLiteDB()

        customAdapter = CustomAdapter(this, employeeList)
    }

    companion object {
        lateinit var BUTTON_CLICKED: String
    }
}