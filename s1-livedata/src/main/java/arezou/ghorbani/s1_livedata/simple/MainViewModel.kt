package arezou.ghorbani.s1_livedata.simple

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    val siteName : MutableLiveData<String> by lazy { MutableLiveData<String>() }

}