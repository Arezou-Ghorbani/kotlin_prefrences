package arezou.ghorbani.s1_viewmodel.recycler

import arezou.ghorbani.s1_viewmodel.Utils
import androidx.lifecycle.ViewModel

class RecyclerViewModel : ViewModel() {

    val itemsList = Utils.getItems()

}