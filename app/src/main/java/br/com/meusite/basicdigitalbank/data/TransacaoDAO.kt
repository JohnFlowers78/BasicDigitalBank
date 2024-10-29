package br.com.meusite.basicdigitalbank.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransacaoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTransacao(transacao: Transacao)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(transacoes: List<Transacao>)          // Inserção de múltiplos itens   -->   Para popular a base de dados

    @Query("SELECT * FROM transacao_table ORDER BY id DESC")
    fun listarTransacao(): LiveData<List<Transacao>>
}