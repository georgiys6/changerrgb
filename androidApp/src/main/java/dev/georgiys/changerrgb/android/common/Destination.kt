package dev.georgiys.changerrgb.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination{
    val title: String
    val route: String
    val routeWithArgs: String
}

object Home: Destination{
    override val title: String
        get() = "ChangerRGB"
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route
}

object Detail: Destination{
    override val title: String
        get() = "Result Connect"

    override val route: String
        get() = "detail"

    override val routeWithArgs: String
        get() = route

}

val changerDestinations = listOf(Home)