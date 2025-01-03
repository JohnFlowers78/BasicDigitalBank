package br.com.meusite.basicdigitalbank.data

import androidx.lifecycle.LiveData

class TransacaoRepository(private val transacaoDao: TransacaoDAO) {

    val listarTransacao: LiveData<List<Transacao>> = transacaoDao.listarTransacao()

    suspend fun addTransacao(transacao: Transacao){
        transacaoDao.addTransacao(transacao)
    }
}