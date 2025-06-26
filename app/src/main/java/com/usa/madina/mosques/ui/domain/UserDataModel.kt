package com.usa.madina.mosques.ui.domain

import com.usa.madina.mosques.repo.data.AuthenticateModel
import com.usa.madina.mosques.repo.data.ClientConfigurationModel
import com.usa.madina.mosques.repo.data.DeviceDetailsModel

data class UserDataModel(val authenticateModel: AuthenticateModel?, val deviceDetailsModel: DeviceDetailsModel,
    val configurationModel: ClientConfigurationModel?)