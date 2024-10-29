package br.com.meusite.basicdigitalbank.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransacaoViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Transacao>>
    private val repository: TransacaoRepository

    init {
        val transacaoDao = AppDatabase.getDatabase(application).transacaoDao()
        repository = TransacaoRepository(transacaoDao)
        readAllData = repository.listarTransacao
    }

    fun addTransacao(transacao: Transacao){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransacao(transacao)
        }
    }
}