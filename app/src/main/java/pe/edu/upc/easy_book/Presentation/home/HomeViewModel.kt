package pe.edu.upc.easy_book.Presentation.home

import dagger.hilt.android.lifecycle.HiltViewModel
import pe.edu.upc.easy_book.domain.BookRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {
    private val _catalog = MutableStateFlow<List<Book>>(emptyList())
    val catalog: StateFlow<List<Book>> = _catalog

    private val _library = MutableStateFlow<List<Book>>(emptyList())
    val library: StateFlow<List<Book>> = _library

    init {
        loadCatalog()
        viewModelScope.launch { repository.getLibrary().collect { _library.value = it } }
    }

    private fun loadCatalog() {
        viewModelScope.launch {
            try { _catalog.value = repository.getCatalog() } catch (e: Exception) { }
        }
    }

    fun removeBook(id: Int) = viewModelScope.launch { repository.removeBook(id) }
}