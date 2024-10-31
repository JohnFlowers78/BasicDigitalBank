package br.com.meusite.basicdigitalbank.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [Transacao::class, Caixinha::class], version = 24, exportSchema = false)
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
                    )
                        .addCallback(DatabaseCallback(context))      // -->  Callback para adicionar a pré-população
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
                        Transacao(0, "Supermercado Pão de Açúcar", 120.50, "09/06/2024", "18:44:16"),
                        Transacao(0, "Restaurante Japonês Sushi Way", 95.30, "08/06/2024", "13:20:26"),
                        Transacao(0, "Combustível Posto Shell", 300.00, "07/06/2024", "09:15:57"),
                        Transacao(0, "Cinema Cinemark", 45.00, "05/06/2024", "20:45:10"),
                        Transacao(0, "Lanchonete Burger King", 32.90, "04/06/2024", "12:30:29"),
                        Transacao(0, "Farmácia Drogasil", 67.80, "03/06/2024", "16:00:32"),
                        Transacao(0, "Loja de Roupas Zara", 250.00, "02/06/2024", "15:10:43"),
                        Transacao(0, "Eletrônicos Fast Shop", 1599.99, "01/06/2024", "10:00:05")
                    )

                    getDatabase(context).transacaoDao().insertAll(transacoes)
                }
                Executors.newSingleThreadExecutor().execute {
                    val caixinhasIniciais = listOf(
                        Caixinha(0, "Viagem", 500.0),
                        Caixinha(0, "Reserva de Emergência", 1000.0),
                        Caixinha(0, "Novo Celular", 200.0)
                    )

                    getDatabase(context).caixinhaDao().insertAll(caixinhasIniciais)
                }
            }
        }
    }
}