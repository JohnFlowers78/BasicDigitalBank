package br.com.meusite.basicdigitalbank.CaixinhaFragments

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.meusite.basicdigitalbank.R
import br.com.meusite.basicdigitalbank.TrasacaoFragments.CreditoFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CaixinhasFContainerVActivity : AppCompatActivity() {

    private lateinit var btnCredito2 : FloatingActionButton
    private lateinit var btnCaixinhas2 : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Ativa o modo Edge-to-Edge
        setContentView(R.layout.activity_caixinhas_fcontainer_vactivity)

        // Ajusta o padding com base nas barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val caixinhaId = intent.getIntExtra("CAIXINHA_ID", -1)

        val fragmentToLoad = intent.getStringExtra("FRAGMENT_TO_LOAD")
        val fragmentToLoad2 = intent.getStringExtra("FRAGMENT_TO_LOAD2")

        if (fragmentToLoad == "AddCaixinhaFragment") {
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerCaixinhas, AddCaixinhaFragment())
                .commit()
        }

        btnCaixinhas2.setOnClickListener {
            val intent = Intent(this, CaixinhasActivity::class.java)
            startActivity(intent)
        }

        if (fragmentToLoad2 == "CreditoFragment") {
            btnCredito2.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.ContainerMain, CreditoFragment())
                    .commit()
            }
        }

        if (caixinhaId != -1) {
            // Carrega o DetalhesCaixinhaFragment com o ID recebido
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerCaixinhas, DetalhesCaixinhaFragment.newInstance(caixinhaId))
                .commit()
        } else {
            // Se necessário, carregar outro fragmento ou um estado padrão
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerCaixinhas, AddCaixinhaFragment())
                .commit()
        }


    }
}
