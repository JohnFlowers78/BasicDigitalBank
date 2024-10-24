package br.com.meusite.basicdigitalbank.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CaixinhaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Caixinha>>
    private val repository: CaixinhaRepository

    init {
        val caixinhaDao = AppDatabase.getDatabase(application).caixinhaDao()
        repository = CaixinhaRepository(caixinhaDao)
        readAllData = repository.listarCaixinha
    }

    fun addCaixinha(caixinha: Caixinha){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCaixinha(caixinha)
        }
    }

    fun atualizarCaixinha(caixinha: Caixinha){
        viewModelScope.launch(Dispatchers.IO) {
            repository.atualizarCaixinha(caixinha)
        }
    }

    fun deletarCaixinha(caixinha: Caixinha){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletarCaixinha(caixinha)
        }
    }
}
