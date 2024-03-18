package io.github.jenusdy.github_search.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("public_repos")
    val publicRepo: Int,
    @SerializedName("followers")
    val follower: Int,
    @SerializedName("following")
    val following: Int
)
