package ericomonteiro.github.com.multitenant.util

import com.google.gson.Gson

fun Any.toJson() = Gson().toJson(this)

fun Any.fromJson(json: String) = Gson().fromJson(json, this::class.java)