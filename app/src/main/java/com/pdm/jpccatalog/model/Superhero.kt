package com.pdm.jpccatalog.model

import androidx.annotation.DrawableRes

data class Superhero(
    var superHeroName: String,
    var realName: String,
    var publisher: String,
    @DrawableRes var picture: Int
)
