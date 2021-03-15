package com.example.sqliteroom

import androidx.room.*


@Dao
interface RoomEmployeeDoa {

    // select query and it will return  a list as shown
    @Query("SELECT * FROM emp_table") // @Query annotation will take a query as shown
    fun getAllEmployeeDetails(): List<EmployeeDataClass>

    @Insert
    fun insertEmployeeDetails(employee: EmployeeDataClass)

    @Update
    fun updateEmployeeDetails(employee: EmployeeDataClass)

    @Delete
    fun deleteEmployeeDetails(employee: EmployeeDataClass)
}
