package com.ridhodev.firstquestion.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ridhodev.firstquestion.data.Repository
import com.ridhodev.firstquestion.data.response.DataItem

class ThirdScreenViewModel: ViewModel() {
    private val repository = Repository()
    val users: LiveData<PagingData<DataItem>> =
        repository.getUsers().cachedIn(viewModelScope)

}
