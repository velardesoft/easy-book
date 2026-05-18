package pe.edu.upc.easy_book.Presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.easy_book.domain.Book
import pe.edu.upc.easy_book.domain.BookRepository
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {
    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    fun loadBook(id: Int) {
        viewModelScope.launch { _book.value = repository.getBookById(id) }
    }

    fun toggleRead() {
        viewModelScope.launch {
            _book.value?.let { current ->
                repository.toggleRead(current)
                _book.value = repository.getBookById(current.id) // Refresca UI
            }
        }
    }
}