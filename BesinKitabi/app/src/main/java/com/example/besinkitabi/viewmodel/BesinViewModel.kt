package com.example.besinkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.besinkitabi.model.Besin
import com.example.besinkitabi.roomDB.BesinDB
import com.example.besinkitabi.services.BesinAPIService
import com.example.besinkitabi.utils.OzelSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BesinViewModel(application: Application) : AndroidViewModel(application) {

    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji = MutableLiveData<Boolean>()
    val besinYukleniyor = MutableLiveData<Boolean>()

    private val ozelsharepref = OzelSharedPreferences(getApplication())
    private val besinApiservis = BesinAPIService()
    private val guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    private fun verileriInternettenAl(){
        besinYukleniyor.value = true
        viewModelScope.launch(Dispatchers.IO){
            val besinListe = besinApiservis.getData()
            withContext(Dispatchers.Main){
                besinYukleniyor.value = false
                roomakaydet(besinListe)
            }
        }
    }
    fun refreshInternetData(){
        verileriInternettenAl()
    }
    fun refreshData(){
        val kaydedilmeZamani = ozelsharepref.getTime()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime()-kaydedilmeZamani<guncellemeZamani){
            verileriRoomdanAl()
        }
        else{
            verileriInternettenAl()
        }
    }
    private fun verileriRoomdanAl(){
        besinYukleniyor.value = true
        viewModelScope.launch(Dispatchers.IO){
            val besinList = BesinDB(getApplication()).besinDao().getAllBesin()
            besinleriGoster(besinList)
            withContext(Dispatchers.Main){
                besinYukleniyor.value = false
            }
        }
    }
    private fun besinleriGoster(besinList: List<Besin>){
        besinler.value = besinList
        besinHataMesaji.value = false
        besinYukleniyor.value = false
    }
    private fun roomakaydet(besinList: List<Besin>){
        viewModelScope.launch{
            val dao = BesinDB(getApplication()).besinDao()
            dao.deleteAllBesin()
            val uuidList = dao.insertAll(*besinList.toTypedArray())
            var i = 0
            while (i < besinList.size){
                besinList[i].uuid = uuidList[i].toInt()
                i = i + 1
            }
            besinleriGoster(besinList)
        }
        ozelsharepref.saveTime(System.nanoTime())
    }


}