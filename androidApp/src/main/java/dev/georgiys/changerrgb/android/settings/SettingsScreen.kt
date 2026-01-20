package dev.georgiys.changerrgb.android.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.georgiys.changerrgb.android.settings.viewstate.SettingsEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    vm: SettingsViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(Modifier.padding(16.dp)) {

            OutlinedTextField(
                value = state.url,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                onValueChange = { vm.dispatch(SettingsEvent.UrlChanged(it)) },
                label = { Text("URL") },
                singleLine = true
            )

            OutlinedTextField(
                value = state.login,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                onValueChange = { vm.dispatch(SettingsEvent.LoginChanged(it)) },
                label = { Text("Логин") },
                singleLine = true
            )

            OutlinedTextField(
                value = state.password,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                onValueChange = { vm.dispatch(SettingsEvent.PasswordChanged(it)) },
                label = { Text("Пароль") },
                singleLine = true
            )

            Button(
                onClick = { vm.dispatch(SettingsEvent.SaveClicked) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Сохранить")
            }

            if (state.isSaved) {
                Text(
                    "Сохранено!",
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            state.error?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
