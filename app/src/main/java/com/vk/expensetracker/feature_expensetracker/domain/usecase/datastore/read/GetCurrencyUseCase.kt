package com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.read

import com.vk.expensetracker.feature_expensetracker.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(private val datastoreRepository: DatastoreRepository) {
    suspend operator fun invoke() : Flow<String> =
        datastoreRepository.readCurrencyFromDataStore()
}