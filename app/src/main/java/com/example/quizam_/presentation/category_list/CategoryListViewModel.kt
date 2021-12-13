package com.example.quizam_.presentation.category_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizam_.common.Resource
import com.example.quizam_.domain.use_case.GetCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategories
) : ViewModel() {
    private val _state = mutableStateOf(CategoryListState())
    val state: State<CategoryListState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CategoryListState(categories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CategoryListState(error = result.message ?: "Error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CategoryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}