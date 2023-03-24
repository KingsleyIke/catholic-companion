package com.kingstek.companion.utils

import com.kingstek.companion.ui.location.MapsFragment

object Constants {

    const val READ_STORAGE_PERMISSION_CODE = 18
    const val LOCATION_PERMISSION_CODE = 30
    const val DEFAULT_ZOOM = 15
    const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    // Keys for storing activity state.
    // [START maps_current_place_state_keys]
    const val KEY_CAMERA_POSITION = "camera_position"
    const val KEY_LOCATION = "location"
    // [END maps_current_place_state_keys]

    // Used for selecting the current place.
    const val M_MAX_ENTRIES = 5

    val TAG = MapsFragment::class.java.simpleName

    const val USERS: String = "users"
    const val DIOCESE: String = "diocese"

}