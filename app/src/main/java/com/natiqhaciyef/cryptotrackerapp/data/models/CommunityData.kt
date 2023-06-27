package com.natiqhaciyef.cryptotrackerapp.data.models

data class CommunityData(
    val facebook_likes: Any,
    val reddit_accounts_active_48h: String,
    val reddit_average_comments_48h: Double,
    val reddit_average_posts_48h: Double,
    val reddit_subscribers: Int,
    val twitter_followers: Int
)