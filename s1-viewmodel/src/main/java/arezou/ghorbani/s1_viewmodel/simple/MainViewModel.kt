package arezou.ghorbani.s1_viewmodel.simple

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var counter = 0

    fun addNumber() {
        counter++
    }
}