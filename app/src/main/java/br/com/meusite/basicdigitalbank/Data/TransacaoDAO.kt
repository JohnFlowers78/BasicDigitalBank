package br.com.meusite.basicdigitalbank.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransacaoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTransacao(transacao: Transacao)

    @Query("SELECT * FROM transacao_table ORDER BY id DESC")
    fun listarTransacao(): LiveData<List<Transacao>>
}