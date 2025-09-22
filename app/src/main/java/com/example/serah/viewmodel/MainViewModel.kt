package com.example.serah.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serah.data.model.BookDoc
import com.example.serah.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _books = MutableLiveData<List<BookDoc>>()

    val books: MutableLiveData<List<BookDoc>>
        get() = _books

    fun fetchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks(query, 30)
                if (response.isSuccessful) {
                    _books.postValue(response.body()?.docs ?: emptyList())
                    Log.d("MainViewModel", "Books fetched successfully")
                } else {
                    _books.postValue(emptyList())
                    Log.e("MainViewModel", "Error fetching books: ${response.code()}")
                }
            } catch (e: Exception) {
                _books.postValue(emptyList())
                Log.e("MainViewModel", "Exception fetching books", e)
            }
        }
    }
}