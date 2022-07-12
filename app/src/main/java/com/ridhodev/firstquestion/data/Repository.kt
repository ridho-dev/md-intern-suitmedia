package com.ridhodev.firstquestion.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.ridhodev.firstquestion.data.response.DataItem
import com.ridhodev.firstquestion.data.retrofit.ApiConfig
import com.ridhodev.firstquestion.data.retrofit.ApiService

class Repository() {
    private val apiService: ApiService = ApiConfig.getApiService()
    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                PagingSource(apiService)
            }
        ).liveData
    }
}