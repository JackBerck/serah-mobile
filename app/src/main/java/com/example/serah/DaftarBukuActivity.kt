package com.example.serah

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serah.data.model.BookDoc
import com.example.serah.databinding.ActivityDaftarBukuBinding
import com.example.serah.ui.adapter.BookAdapter
import com.example.serah.ui.adapter.OnBookClickListener
import com.example.serah.ui.fragment.BookDetailFragment
import com.example.serah.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity(), OnBookClickListener {
    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = BookAdapter(emptyList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this) { books ->
            adapter.setData(books)
        }

        viewModel.fetchBooks("harry potter")
    }

    override fun onBookClick(book: BookDoc) {
        book.let { b ->
            BookDetailFragment (
                title = b.title ?: "No Title",
                author = b.authorName?.joinToString(", ") ?: "Unknown Author",
                year = b.firstPublishYear?.toString() ?: "Unknown Year",
                coverId = b.coverId?: 0
            ).show(supportFragmentManager, BookDetailFragment::class.java.simpleName)
        }
    }
}