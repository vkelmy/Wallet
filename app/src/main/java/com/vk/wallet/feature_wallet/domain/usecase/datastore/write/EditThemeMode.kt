package com.vk.wallet.feature_wallet.domain.usecase.datastore.write

import com.vk.wallet.feature_wallet.domain.repository.DatastoreRepository
import javax.inject.Inject

class EditThemeMode @Inject constructor(private val datastoreRepository: DatastoreRepository) {
    suspend operator fun invoke(mode: Boolean) {
        datastoreRepository.writeDarkMode(mode)
    }
}