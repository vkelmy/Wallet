package com.vk.wallet.feature_wallet.domain.usecase.datastore.write

import com.vk.wallet.feature_wallet.domain.repository.DatastoreRepository
import javax.inject.Inject

class EditLimitDurationUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(duration: Int) {
        return datastoreRepository.writeLimitDurationToDataStore(duration)
    }
}