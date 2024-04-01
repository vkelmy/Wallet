package com.vk.wallet.feature_wallet.domain.usecase.datastore.write

import com.vk.wallet.feature_wallet.domain.repository.DatastoreRepository
import javax.inject.Inject

class EraseDatastoreUseCase @Inject constructor(private val datastoreRepository: DatastoreRepository) {
    suspend operator fun invoke() {
        datastoreRepository.eraseDatastore()
    }
}