package br.com.meusite.basicdigitalbank.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CaixinhaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCaixinha(caixinha: Caixinha)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(caixinhas: List<Caixinha>)  // Para popular uma base de dados ja pronta (facilitar testes)

    @Update
    fun atualizarCaixinha(caixinha: Caixinha)

    @Delete
    fun deletarCaixinha(caixinha: Caixinha)

    @Query("SELECT * FROM caixinha_table")
    fun listarCaixinha(): LiveData<List<Caixinha>>

    @Query("SELECT * FROM caixinha_table WHERE id = :caixinhaId")
    fun getCaixinhaById(caixinhaId: Int): LiveData<Caixinha>
}