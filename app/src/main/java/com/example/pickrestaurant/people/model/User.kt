package com.example.pickrestaurant.people.model

import android.graphics.Bitmap

/**
 * Created by efren on 12/07/2018.
 */
data class User(val userId: Int, val name: String, val email: String, val password: String, val description: String, @Transient var picture: Bitmap, var pictureUrl: String )