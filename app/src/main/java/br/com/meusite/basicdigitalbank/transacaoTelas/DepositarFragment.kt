package br.com.meusite.basicdigitalbank.transacaoTelas

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.meusite.basicdigitalbank.data.Transacao
import br.com.meusite.basicdigitalbank.data.TransacaoViewModel
import br.com.meusite.basicdigitalbank.MainActivity
import br.com.meusite.basicdigitalbank.R
import com.google.android.material.textfield.TextInputEditText

class DepositarFragment : Fragment() {

    lateinit var txtEditValorDep: TextInputEditText
    lateinit var txtEditDescricao: TextInputEditText
    lateinit var btnDepositar2: Button
    lateinit var mTransacaoViewModel: TransacaoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_depositar, container, false)

        txtEditValorDep = view.findViewById(R.id.txtEditValorDep)
        txtEditDescricao = view.findViewById(R.id.txtEditDescricao)
        btnDepositar2 = view.findViewById(R.id.btnDepositar2)
        mTransacaoViewModel = ViewModelProvider(this).get(TransacaoViewModel::class.java)

        btnDepositar2.setOnClickListener {
            val valorDep = txtEditValorDep.text.toString()
            val descricao = txtEditDescricao.text.toString()

            if (!TextUtils.isEmpty(valorDep)) {
                val valor = valorDep.toDoubleOrNull()
                if (valor != null) {
                    val transacao = Transacao(0, descricao, valor) // Passa valor como Double
                    mTransacaoViewModel.addTransacao(transacao)

                    // Atualiza o saldo na MainActivity
                    (activity as MainActivity).atualizarSaldoTransacao(valor, "deposito")

                    Toast.makeText(requireContext(), "Transferência Concluída!", Toast.LENGTH_LONG).show()
                    voltar()
                } else {
                    Toast.makeText(requireContext(), "Valor inválido! Por favor, insira um número válido.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Erro ao Transferir. Preencha todos os campos.", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
    fun voltar(){
        parentFragmentManager.beginTransaction()
            .replace(R.id.ContainerMain, CreditoFragment())
            .commit()
    }
}