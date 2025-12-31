package dev.georgiys.changerrgb.android.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.georgiys.changerrgb.android.settings.viewstate.SettingsEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    vm: SettingsViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()

    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = state.url,
            onValueChange = { vm.dispatch(SettingsEvent.UrlChanged(it)) },
            label = { Text("URL") }
        )

        OutlinedTextField(
            value = state.login,
            onValueChange = { vm.dispatch(SettingsEvent.LoginChanged(it)) },
            label = { Text("Логин") }
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = { vm.dispatch(SettingsEvent.PasswordChanged(it)) },
            label = { Text("Пароль") }
        )

        Button(
            onClick = { vm.dispatch(SettingsEvent.SaveClicked) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Сохранить")
        }

        if (state.isSaved) {
            Text("Сохранено!", color = Color.Green)
        }

        state.error?.let {
            Text(text = it, color = Color.Red)
        }
    }
}
