package com.usa.madina.mosques.ui.domain

import com.usa.madina.mosques.repo.data.AuthenticateModel
import com.usa.madina.mosques.repo.data.ClientConfigurationModel
import com.usa.madina.mosques.repo.data.DeviceDetailsModel
import kotlinx.serialization.Serializable

@Serializable
data class UserDataModel(
    @Transient val authenticateModel: AuthenticateModel?,
    @Transient val deviceDetailsModel: DeviceDetailsModel?,
    @Transient val configurationModel: ClientConfigurationModel?)