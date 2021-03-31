package com.example.jetpack.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack.PersonDataClass
import com.example.jetpack.database.DatabaseBuilder
import java.util.concurrent.Executors

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var allPersons: MutableLiveData<List<PersonDataClass>> = MutableLiveData()
    private val roomDatabaseBuilder = DatabaseBuilder.getInstance(context)

    fun getAllPersonsDetails(): LiveData<List<PersonDataClass>> {
//        Executors.newSingleThreadExecutor().execute {
//            val list = roomDatabaseBuilder.personDao().getAllPersonsDetails()
//            allPersons.postValue(list)

        return roomDatabaseBuilder.personDao().getAllPersonsDetails()
    }

    fun addPersonDetails(personDataClass: PersonDataClass) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.personDao().insertPersonDetails(
                PersonDataClass(
                    personId = personDataClass.personId,
                    personName = personDataClass.personName,
                    personContact = personDataClass.personContact,
                    personAddress = personDataClass.personAddress
                )
            )
        }
    }
}