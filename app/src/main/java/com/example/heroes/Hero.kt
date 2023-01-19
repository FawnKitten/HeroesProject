package com.example.heroes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero (
    val name: String,
    val description: String,
    val superpower: String,
    val ranking: Int,
    val image: String
) : Parcelable, Comparable<Hero> {
    override fun compareTo(other: Hero): Int = this.ranking - other.ranking
}
