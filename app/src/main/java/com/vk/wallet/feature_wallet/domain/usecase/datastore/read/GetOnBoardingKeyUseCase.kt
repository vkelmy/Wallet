package com.vk.wallet.feature_wallet.domain.usecase.datastore.read

import com.vk.wallet.feature_wallet.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnBoardingKeyUseCase @Inject constructor(private val datastoreRepository: DatastoreRepository) {
    suspend operator fun invoke() : Flow<Boolean> {
        return datastoreRepository.readOnBoardingKeyFromDataStore()
    }
}