package com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write

import com.vk.expensetracker.feature_expensetracker.domain.repository.DatastoreRepository
import javax.inject.Inject

class EditLimitDurationUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(duration: Int) {
        return datastoreRepository.writeLimitDurationToDataStore(duration)
    }
}