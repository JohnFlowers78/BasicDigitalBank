package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.meusite.basicdigitalbank.MainActivity
import br.com.meusite.basicdigitalbank.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InvestimentosActivity : AppCompatActivity() {

    private lateinit var btnCredito2 : FloatingActionButton
    private lateinit var btnCaixinhas2 : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_investimentos)

        // Ajusta o padding com base nas barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Verifica se já existe um fragmento carregado (para evitar carregar novamente)
        if (savedInstanceState == null) {
            // Se nenhum fragmento foi carregado anteriormente, carrega o CaixnhasFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.CaixnhaContainer, CaixnhasFragment())
                .commit()
        }

        // Obtém o ID da caixinha da Intent
        val caixinhaId = intent.getIntExtra("CAIXINHA_ID", -1)

        // Obtém o tipo de fragmento a ser carregado
        val fragmentToLoad = intent.getStringExtra("FRAGMENT_TO_LOAD")

        // Carrega o fragmento correspondente se um ID válido foi passado
        if (caixinhaId != -1) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.CaixnhaContainer, DetalhesCaixinhaFragment.newInstance(caixinhaId))
                .commit()
        } else if (fragmentToLoad == "AddCaixinhaFragment") {
            supportFragmentManager.beginTransaction()
                .replace(R.id.CaixnhaContainer, AddCaixinhaFragment())
                .commit()
        }

        btnCredito2 = findViewById(R.id.btnCredito2)
        btnCaixinhas2 = findViewById(R.id.btnCaixinhas2)

        btnCredito2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnCaixinhas2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.CaixnhaContainer, CaixnhasFragment())
                .commit()
        }
    }
}
