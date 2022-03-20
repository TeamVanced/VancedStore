package com.vanced.store.util

fun <T> MutableList<T>.repopulate(items: List<T>) {
    clear()
    addAll(items)
}