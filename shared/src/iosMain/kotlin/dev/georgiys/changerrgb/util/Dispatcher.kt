package dev.georgiys.changerrgb.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IOSDispatcher: Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}

internal actual fun provideDispatcher(): Dispatcher = IOSDispatcher()