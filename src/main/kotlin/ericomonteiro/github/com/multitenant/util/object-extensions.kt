package ericomonteiro.github.com.multitenant.util

import com.google.gson.Gson
import kotlin.reflect.KClass

fun Any.toJson(): String = Gson().toJson(this)
