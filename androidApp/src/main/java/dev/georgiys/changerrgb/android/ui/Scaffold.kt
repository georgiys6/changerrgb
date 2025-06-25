package dev.georgiys.changerrgb.android.ui

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShowResult(
    isSuccess: Boolean,
    snackBarHostState: SnackbarHostState,
    resetSuccess: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = "Всё успешно",
                    duration = SnackbarDuration.Short
                )
                resetSuccess() // ← вызов функции
            }
        }
    }
}
