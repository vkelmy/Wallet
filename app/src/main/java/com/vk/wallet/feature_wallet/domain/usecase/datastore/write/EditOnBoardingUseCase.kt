package com.vk.wallet.feature_wallet.domain.usecase.datastore.write

import com.vk.wallet.feature_wallet.domain.repository.DatastoreRepository
import javax.inject.Inject

class EditOnBoardingUseCase @Inject constructor(private val datastoreRepository: DatastoreRepository) {
    suspend operator fun invoke(completed: Boolean) {
        datastoreRepository.writeOnBoardingKeyToDataStore(completed)
    }
}