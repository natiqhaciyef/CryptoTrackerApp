package com.natiqhaciyef.cryptotrackerapp.data.models

data class DeveloperData(
    val closed_issues: Int,
    val code_additions_deletions_4_weeks: CodeAdditionsDeletions4Weeks,
    val commit_count_4_weeks: Int,
    val forks: Int,
    val pull_request_contributors: Int,
    val pull_requests_merged: Int,
    val stars: Int,
    val subscribers: Int,
    val total_issues: Int
)