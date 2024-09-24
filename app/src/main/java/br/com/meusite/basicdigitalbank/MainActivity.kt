package br.com.meusite.basicdigitalbank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var textUserName : TextView
    private lateinit var bttnExtrato : Button
    private lateinit var bttnCredito : Button
    private lateinit var button4 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textUserName = findViewById(R.id.textUserName)
        encontrarElementos()

        bttnCredito.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerMain, CreditoFragment())
                .commit()
        }

        button4.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerMain, CaixinhasFragment())
                .commit()
        }

        bttnExtrato.setOnClickListener {
            val intent = Intent(this, ExtratoActivity::class.java)

            startActivity(intent)
        }

    }
    private fun encontrarElementos(){
        bttnCredito = findViewById(R.id.bttnCredito)
        bttnExtrato = findViewById(R.id.bttnExtrato)
        bttnExtrato = findViewById(R.id.bttnExtrato)
        button4 = findViewById(R.id.button4)
    }
}