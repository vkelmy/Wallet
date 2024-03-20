package com.vk.expensetracker.feature_expensetracker.domain.usecase.datastore.write

import com.vk.expensetracker.feature_expensetracker.domain.repository.DatastoreRepository
import javax.inject.Inject

class EditOnBoardingUseCase @Inject constructor(private val datastoreRepository: DatastoreRepository) {
    suspend operator fun invoke(completed: Boolean) {
        datastoreRepository.writeOnBoardingKeyToDataStore(completed)
    }
}