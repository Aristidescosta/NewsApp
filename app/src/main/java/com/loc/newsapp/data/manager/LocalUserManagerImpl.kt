package com.loc.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocalUserManager
import com.loc.newsapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Implementação das preferências de histórico de dados
//Permite salvar um valor localmente no dispositivo
class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    //Alterando o valor pra true.
    //Ex: user_settings["app_entry"] = true
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKey.APP_ENTRY]?:false
        }
    }

}

//Criando uma instância do armazenamento(criar valor da extensão)
/*
* DataStore<Preferences>: Define o tipo da propriedade dataStore. DataStore é uma classe para armazenar dados de forma assíncrona e segura. Preferences é um tipo genérico usado para armazenar pares chave-valor.
* */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

private object PreferencesKey{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}