package br.com.meusite.basicdigitalbank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.meusite.basicdigitalbank.data.Transacao

class ExtratoActivity : AppCompatActivity() {

    private lateinit var rvTransacoes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extrato)

        rvTransacoes = findViewById(R.id.rvTransacoes)

        val transacoes = listOf(
            Transacao(0,"Supermercado Pão de Açúcar", 120.50, "09/06/24", "18:44"),
            Transacao(0, "Restaurante Japonês Sushi Way", 95.30, "08/06/24", "13:20"),
            Transacao(0,"Combustível Posto Shell", 300.00, "07/06/24", "09:15"),
            Transacao(0,"Cinema Cinemark", 45.00, "05/06/24", "20:45"),
            Transacao(0, "Lanchonete Burger King", 32.90, "04/06/24", "12:30"),
            Transacao(0, "Farmácia Drogasil", 67.80, "03/06/24", "16:00"),
            Transacao(0, "Loja de Roupas Zara", 250.00, "02/06/24", "15:10"),
            Transacao(0, "Eletrônicos Fast Shop", 1599.99, "01/06/24", "10:00")
        )

        rvTransacoes.adapter = TransacaoAdapter(transacoes)
        rvTransacoes.layoutManager = LinearLayoutManager(this)
    }
}