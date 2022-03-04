package com.sandip.supercoin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sandip.supercoin.Repository.coinrepository

class MainViewModelFactory(private val repository:coinrepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}