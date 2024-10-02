package com.example.besinkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.besinkitabi.model.Besin
import com.example.besinkitabi.roomDB.BesinDB
import kotlinx.coroutines.launch

class BesinDetailsViewModel(application: Application) : AndroidViewModel(application) {
    val besinLiveData = MutableLiveData<Besin?>()
    fun roomVerisiniAl(uuid: Int) {
        viewModelScope.launch {
            val dao = BesinDB(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }


}