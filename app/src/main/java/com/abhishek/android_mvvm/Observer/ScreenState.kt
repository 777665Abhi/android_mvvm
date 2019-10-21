package com.abhishek.android_mvvm.Observer

sealed class ScreenState <out T> {
    object Loading : ScreenState<Nothing>()
    class Render<T>(val renderState: T) : ScreenState<T>()
}