package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.meusite.basicdigitalbank.data.Caixinha
import br.com.meusite.basicdigitalbank.data.CaixinhaViewModel
import br.com.meusite.basicdigitalbank.R
import com.google.android.material.textfield.TextInputEditText

class AddCaixinhaFragment : Fragment() {

    private lateinit var txtCaixinhaNome: TextInputEditText
    private lateinit var txtCaixinhaValor: TextInputEditText
    private lateinit var btnCriar: Button
    private lateinit var mCaixinhaViewModel: CaixinhaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_caixinha, container, false)

        // Inicialização dos componentes
        txtCaixinhaNome = view.findViewById(R.id.txtEditNomeCaixinha)
        txtCaixinhaValor = view.findViewById(R.id.txtEditValorInicial)
        btnCriar = view.findViewById(R.id.btnCriarCaixinha)

        mCaixinhaViewModel = ViewModelProvider(this).get(CaixinhaViewModel::class.java)

        btnCriar.setOnClickListener {
            val nome = txtCaixinhaNome.text.toString()
            val valor = txtCaixinhaValor.text.toString()

            if (!TextUtils.isEmpty(valor)) {
                val valorDouble = valor.toDoubleOrNull()
                if (valorDouble != null) {
                    criarNovaCaixinha(nome, valorDouble)
                    voltar()
                } else {
                    Toast.makeText(requireContext(), "Valor inválido! Por favor, insira um número válido.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Erro ao Criar. Preencha todos os campos.", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun voltar() {
        val intent = Intent(requireActivity(), InvestimentosActivity::class.java)
        startActivity(intent)
    }

    private fun criarNovaCaixinha(nome: String, valorNecessario: Double) {
        // Cria a nova caixinha e atualiza o banco de dados
        val novaCaixinha = Caixinha(0, nome, valorNecessario)
        mCaixinhaViewModel.addCaixinha(novaCaixinha)

        Toast.makeText(requireContext(), "Caixinha criada!", Toast.LENGTH_LONG).show()
        voltar()
    }
}
