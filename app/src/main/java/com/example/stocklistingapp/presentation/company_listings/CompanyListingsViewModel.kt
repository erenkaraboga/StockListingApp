package com.example.stocklistingapp.presentation.company_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocklistingapp.common.constants.Notification
import com.example.stocklistingapp.domain.repository.StockRepository
import com.example.stocklistingapp.common.resources.Resource
import com.example.stocklistingapp.domain.model.NotificationDataModel
import com.example.stocklistingapp.domain.model.NotificationModel
import com.example.stocklistingapp.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository,
    private val notificationRepository: NotificationRepository
): ViewModel() {

    var state by mutableStateOf(CompanyListingsState())

    private var searchJob: Job? = null

    init {
        getCompanyListings()
    }
    var token by mutableStateOf("")
        private set

    fun onEvent(event: CompanyListingsEvent) {
        when(event) {
            is CompanyListingsEvent.Refresh -> {
                getCompanyListings(fetchFromRemote = true)
            }
            is CompanyListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    private fun getCompanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getCompanyListings(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
    fun onSendNotification(companyName : String) {
        viewModelScope.launch {
            try {
                val response = notificationRepository.postNotification(
                    NotificationModel(
                        data = NotificationDataModel(
                            title = "CompanyClicked",
                            message = companyName
                        ),
                        to = token.ifBlank { Notification.TOPIC }
                    )
                )
                if(response.isSuccessful){
                    println("success")
                }
            } catch (e: HttpException) {
                println("raheem: ${e.message()}")
            }
        }
    }

}