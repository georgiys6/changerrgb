package dev.georgiys.changerrgb.android.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.georgiys.changerrgb.android.settings.viewstate.SettingsEvent
import dev.georgiys.changerrgb.android.settings.viewstate.SettingsState
import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.domain.usecase.GetAuthorisationUseCase
import dev.georgiys.changerrgb.domain.usecase.GetSettingsUseCase
import dev.georgiys.changerrgb.domain.usecase.SaveSettingsUseCase
import dev.georgiys.changerrgb.domain.usecase.SaveTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getAuthorisationUseCase: GetAuthorisationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        dispatch(SettingsEvent.Load)
    }

    fun dispatch(event: SettingsEvent) {
        when (event) {

            is SettingsEvent.UrlChanged ->
                _state.update { it.copy(url = event.value) }

            is SettingsEvent.LoginChanged ->
                _state.update { it.copy(login = event.value) }

            is SettingsEvent.PasswordChanged ->
                _state.update { it.copy(password = event.value) }

            SettingsEvent.SaveClicked -> viewModelScope.launch(Dispatchers.IO) { save() }

            SettingsEvent.Load -> load()
        }
    }

    private fun load() {
        try {
            val s = getSettingsUseCase()
            _state.update {
                it.copy(
                    url = s.url,
                    login = s.login,
                    password = s.password,
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            _state.update { it.copy(error = e.message) }
        }
    }

    private suspend fun save() {
        try {
            val st = _state.value
            saveSettingsUseCase(st.url, st.login, st.password)

            val authRequest = getAuthorisationUseCase(Authorisation(st.url, st.login, st.password))
            if (authRequest.status == "success") {
                saveTokenUseCase(authRequest.token)
            } else {
                throw Exception(authRequest.error)
            }
            _state.update { it.copy(isSaved = true) }

        } catch (e: Exception) {
            _state.update { it.copy(error = e.message) }
        }
    }
}


