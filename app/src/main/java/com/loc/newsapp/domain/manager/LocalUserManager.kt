package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

//Interface para salvar a entrada no aplicativo quando o usuário clicar em "Início"
interface LocalUserManager {
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}