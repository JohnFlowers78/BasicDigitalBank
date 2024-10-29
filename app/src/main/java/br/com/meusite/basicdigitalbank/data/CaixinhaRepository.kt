package br.com.meusite.basicdigitalbank.data

import androidx.lifecycle.LiveData

class CaixinhaRepository(private val caixinhaDao: CaixinhaDAO) {

    val listarCaixinha: LiveData<List<Caixinha>> = caixinhaDao.listarCaixinha()

    suspend fun addCaixinha(caixinha: Caixinha){
        caixinhaDao.addCaixinha(caixinha)
    }

    suspend fun atualizarCaixinha(caixinha: Caixinha){
        caixinhaDao.atualizarCaixinha(caixinha)
    }

    suspend fun deletarCaixinha(caixinha: Caixinha){
        caixinhaDao.deletarCaixinha(caixinha)
    }
}