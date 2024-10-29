package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.meusite.basicdigitalbank.data.AppDatabase
import br.com.meusite.basicdigitalbank.data.Caixinha
import br.com.meusite.basicdigitalbank.data.CaixinhaViewModel
import br.com.meusite.basicdigitalbank.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CaixinhasActivity : AppCompatActivity() {

    private lateinit var rvCaixinhas: RecyclerView
    private lateinit var caixinhaViewModel: CaixinhaViewModel
    private lateinit var caixinhaAdapter: CaixinhaAdapter
    private lateinit var btnAddCaixinha: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caixinhas)

        rvCaixinhas = findViewById(R.id.rvCaixinhas)
        btnAddCaixinha = findViewById(R.id.btnAddCaixinha)


        rvCaixinhas.layoutManager = GridLayoutManager(this, 2)
        caixinhaAdapter = CaixinhaAdapter(emptyList()) { caixinha ->
            val fragment = DetalhesCaixinhaFragment.newInstance(caixinha.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerCaixinhas, fragment)
                .addToBackStack(null)
                .commit()
        }
        rvCaixinhas.adapter = caixinhaAdapter


        caixinhaViewModel = ViewModelProvider(this).get(CaixinhaViewModel::class.java)

        // Observando mudanças nos dados e atualizando a UI
        caixinhaViewModel.readAllData.observe(this) { caixinhas ->
            caixinhaAdapter.updateData(caixinhas)
        }

        btnAddCaixinha.setOnClickListener {
            val fragment = AddCaixinhaFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerCaixinhas, fragment)
                .addToBackStack(null)
                .commit()
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                preencherBaseCaixinhas()
            }
        }

    }

    // Método para popular a base de caixinhas iniciais
    private fun preencherBaseCaixinhas() {
        val caixinhasIniciais = listOf(
            Caixinha(0, "Viagem", 500.0),
            Caixinha(0, "Reserva de Emergência", 1000.0),
            Caixinha(0, "Novo Celular", 200.0)
        )

        val db = AppDatabase.getDatabase(this)
        db.caixinhaDao().insertAll(caixinhasIniciais)
    }
}
