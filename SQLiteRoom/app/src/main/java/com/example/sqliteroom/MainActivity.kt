package com.example.sqliteroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addSQLiteDummyDataBtn.setOnClickListener {
            insertDataInDBUsingSQLite()
            Toast.makeText(this, "Data added successfully", Toast.LENGTH_LONG).show()
        }

        sqliteDemoBtn.setOnClickListener {
            startActivity(Intent(this, DatabaseActivity::class.java).putExtra(BUTTON_CLICKED_KEY, SQLITE_DEMO_BTN))
        }

        addRoomDummyDataBtn.setOnClickListener {
            insertDataInDBUsingRoom()
            Toast.makeText(this, "Data added successfully", Toast.LENGTH_LONG).show()
        }

        RoomDemoBtn.setOnClickListener {
            startActivity(Intent(this, DatabaseActivity::class.java).putExtra(BUTTON_CLICKED_KEY, ROOM_DEMO_BTN))
        }
    }

    // Insertion of data using Room
    private fun insertDataInDBUsingRoom() {
        val database = RoomDatabaseBuilder.getInstance(this)

        // Separate Thread i.e Executors are used to insert data if not used application will crash
        Executors.newSingleThreadExecutor().execute {
            database.employeeDao().insertEmployeeDetails(EmployeeDataClass(name = "Ritik Jain", contact = "7409959167", address = "Meerut"))
            database.employeeDao().insertEmployeeDetails(EmployeeDataClass(name = "Adarsh", contact = "123456789", address = "Meerut"))
            database.employeeDao().insertEmployeeDetails(EmployeeDataClass(name = "Prateek", contact = "982778368", address = "Noida"))

        }
    }
// Insertion of data in database using SQLite
    private fun insertDataInDBUsingSQLite() {

        val databaseManager = SQLiteDatabaseManager(this)

        // insert data in DB
        databaseManager.insertValue(EmployeeDataClass(name = "Ritik Jain", contact = "7409959167", address = "Meerut"))
        databaseManager.insertValue(EmployeeDataClass(name = "Ashish", contact = "123456789", address = "Delhi"))
        databaseManager.insertValue(EmployeeDataClass(name = "Akshay", contact = "3264464789", address = "New Delhi"))
    }

    companion object {
        const val BUTTON_CLICKED_KEY = "BUTTON_CLICKED"
        const val SQLITE_DEMO_BTN = "SQLITE_DEMO_BTN"
        const val ROOM_DEMO_BTN = "ROOM_DEMO_BTN"
    }
    }
