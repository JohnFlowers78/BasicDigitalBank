package br.com.meusite.basicdigitalbank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.meusite.basicdigitalbank.CaixinhaFragments.CaixinhasActivity
import br.com.meusite.basicdigitalbank.TrasacaoFragments.CreditoFragment
import br.com.meusite.basicdigitalbank.TrasacaoFragments.ExtratoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var textUserName : TextView
    private lateinit var bttnExtrato : Button
    private lateinit var btnCredito : FloatingActionButton
    private lateinit var btnCaixinhas : FloatingActionButton
    private var saldoTotal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        encontrarElementos()

        btnCredito.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerMain, CreditoFragment())
                .commit()
        }

        btnCaixinhas.setOnClickListener {
            val intent = Intent(this, CaixinhasActivity::class.java)
            startActivity(intent)
        }

        bttnExtrato.setOnClickListener {
            val intent = Intent(this, ExtratoActivity::class.java)

            startActivity(intent)
        }

    }
    private fun encontrarElementos(){
        textUserName = findViewById(R.id.textUserName)
        btnCredito = findViewById(R.id.btnCredito)
        bttnExtrato = findViewById(R.id.bttnExtrato)
        btnCaixinhas = findViewById(R.id.btnCaixinhas)
    }

    fun getSaldoTotal(): Double {
        return saldoTotal
    }

    fun setSaldoTotal(novoSaldo: Double) {
        saldoTotal = novoSaldo
        atualizarSaldo()
    }

    fun atualizarSaldo() {
        val textSaldo = findViewById<TextView>(R.id.textSaldo)
        textSaldo.text = "R$ %.2f".format(saldoTotal)
    }

    // Função para diminuir o saldo (nova função)
    fun diminuirSaldo(valor: Double) {
        saldoTotal -= valor
        atualizarSaldo() // Atualiza a interface ou qualquer lógica associada ao saldo
    }

    fun atualizarSaldoTransacao(valor: Double, tipoTransacao: String) {
        when (tipoTransacao) {
            "deposito" -> saldoTotal += valor
            "transferencia" -> if (saldoTotal >= valor) {
                saldoTotal -= valor
            } else {
                // Tratar saldo insuficiente
                mostrarErroSaldoInsuficiente()
            }
        }
        atualizarSaldo()
    }

    fun mostrarErroSaldoInsuficiente() {
        Toast.makeText(this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show()
    }
}