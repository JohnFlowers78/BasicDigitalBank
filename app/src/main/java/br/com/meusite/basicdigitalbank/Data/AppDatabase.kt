package br.com.meusite.basicdigitalbank.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Transacao::class, Caixinha::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun transacaoDao(): TransacaoDAO
    abstract fun caixinhaDao(): CaixinhaDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }else {

                kotlin.synchronized(this) {

                    val instance = Room.databaseBuilder(         //método que constrói o DataBase
                        context.applicationContext,               // Recebe o ctx da aplicação
                        AppDatabase::class.java,                   // Recebe a classe do banco de dados
                        "mydigitalbank_db"                     // Recebe o nome do "banco de dados"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}