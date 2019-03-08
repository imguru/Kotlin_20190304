package xyz.ourguide.lge.simplegithub.model

import com.google.gson.annotations.SerializedName
import java.util.*

// -keep class xyz.ourguide.lge.simplegithub.model.** { *; }

data class User(
    val login: String,
    val id: Int,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    val location: String
)

data class RepoSearchResponse(
    @field:SerializedName("total_count") val totalCount: Int,
    val items: List<GithubRepo>
)

data class GithubRepo(
    val id: Int,
    val name: String,
    @field:SerializedName("full_name") val fullName: String,
    val owner: User,
    val language: String,
    @field:SerializedName("created_at") val createdAt: Date,
    @field:SerializedName("updated_at") val updatedAt: Date,
    val forks: Int
)






