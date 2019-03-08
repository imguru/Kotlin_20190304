package xyz.ourguide.lge.simplegithub.model

import com.google.gson.annotations.SerializedName

// -keep class xyz.ourguide.lge.simplegithub.model.** { *; }

data class User(
    val login: String,
    val id: Int,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    val location: String
)