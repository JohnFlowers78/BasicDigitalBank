package br.com.meusite.basicdigitalbank.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Parcelize
@Entity(tableName = "transacao_table")
data class Transacao(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val descricao: String,
    val valor: Double,
    val data: String = getCurrentDate(),
    val hora: String = getCurrentTime()
): Parcelable {
    companion object {
        fun getCurrentDate(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")  // Formato de data
            return current.format(formatter)
        }

        fun getCurrentTime(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")  // Formato de hora
            return current.format(formatter)
        }
    }
}