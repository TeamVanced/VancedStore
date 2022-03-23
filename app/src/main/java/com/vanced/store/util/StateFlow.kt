package com.vanced.store.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KProperty

fun <T> mutableStateFlow(initial: T): MutableStateFlow<T> {
    return MutableStateFlow(initial)
}

operator fun <V> MutableStateFlow<V>.getValue(
    value: V,
    property: KProperty<*>
): V {
    return this.value
}

operator fun <V> MutableStateFlow<V>.setValue(
    thisRef: Any?,
    property: KProperty<*>,
    value: V
) {
    this.value = value
}

