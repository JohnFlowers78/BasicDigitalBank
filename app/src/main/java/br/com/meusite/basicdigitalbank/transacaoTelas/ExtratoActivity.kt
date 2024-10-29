package br.com.meusite.basicdigitalbank.transacaoTelas

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.meusite.basicdigitalbank.data.Transacao
import br.com.meusite.basicdigitalbank.R
import br.com.meusite.basicdigitalbank.data.TransacaoViewModel

class ExtratoActivity : AppCompatActivity() {

    private lateinit var rvTransacoes: RecyclerView
    private lateinit var transacaoAdapter: TransacaoAdapter
    private val mTransacaoViewModel: TransacaoViewModel by viewModels() // Inicializando o ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extrato)

        rvTransacoes = findViewById(R.id.rvTransacoes)
        transacaoAdapter = TransacaoAdapter(emptyList()) // Começamos com uma lista vazia
        rvTransacoes.adapter = transacaoAdapter
        rvTransacoes.layoutManager = LinearLayoutManager(this)

        // Observar mudanças na lista de transações
        mTransacaoViewModel.readAllData.observe(this, Observer { transacoes ->
            transacaoAdapter.updateList(transacoes) // Atualizar Adapter com a nova lista de transações
        })
    }
}