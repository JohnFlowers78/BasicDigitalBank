package br.com.meusite.basicdigitalbank.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "caixinha_table")
data class Caixinha(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val saldo: Double
): Parcelable
