package br.com.meusite.basicdigitalbank.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [Transacao::class, Caixinha::class], version = 7, exportSchema = false)
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

                synchronized(this) {

                    val instance = Room.databaseBuilder(         // método que constrói o DataBase
                        context.applicationContext,               // Recebe o ctx da aplicação
                        AppDatabase::class.java,                   // Recebe a classe do banco de dados
                        "mydigitalbank_db"                     // Nome do Banco
                    ).addCallback(DatabaseCallback(context))       // -->  Callback para adicionar a pré-população
                     .build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
        private class DatabaseCallback(
            private val context: Context
        ) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                // Executa em uma thread separada para não bloquear a UI
                Executors.newSingleThreadExecutor().execute {
                    val transacoes = listOf(
                        Transacao(0, "Supermercado Pão de Açúcar", 120.50, "09/06/24", "18:44"),
                        Transacao(0, "Restaurante Japonês Sushi Way", 95.30, "08/06/24", "13:20"),
                        Transacao(0, "Combustível Posto Shell", 300.00, "07/06/24", "09:15"),
                        Transacao(0, "Cinema Cinemark", 45.00, "05/06/24", "20:45"),
                        Transacao(0, "Lanchonete Burger King", 32.90, "04/06/24", "12:30"),
                        Transacao(0, "Farmácia Drogasil", 67.80, "03/06/24", "16:00"),
                        Transacao(0, "Loja de Roupas Zara", 250.00, "02/06/24", "15:10"),
                        Transacao(0, "Eletrônicos Fast Shop", 1599.99, "01/06/24", "10:00")
                    )

                    getDatabase(context).transacaoDao().insertAll(transacoes)
                }
            }
        }
    }
}