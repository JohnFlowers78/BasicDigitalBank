package br.com.meusite.basicdigitalbank.TrasacaoFragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.meusite.basicdigitalbank.Data.Transacao
import br.com.meusite.basicdigitalbank.Data.TransacaoViewModel
import br.com.meusite.basicdigitalbank.MainActivity
import br.com.meusite.basicdigitalbank.R
import com.google.android.material.textfield.TextInputEditText

class TransferirFragment : Fragment() {

    lateinit var txtEditChavePix: TextInputEditText
    lateinit var txtEditValorTransfer: TextInputEditText
    lateinit var btnTransferir: Button
    lateinit var mTransacaoViewModel: TransacaoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transferir, container, false)

        txtEditChavePix = view.findViewById(R.id.txtEditChavePix)
        txtEditValorTransfer = view.findViewById(R.id.txtEditValorTransfer)
        btnTransferir = view.findViewById(R.id.btnTransferir2)
        mTransacaoViewModel = ViewModelProvider(this).get(TransacaoViewModel::class.java)

        btnTransferir.setOnClickListener {
            val chavePix = txtEditChavePix.text.toString()
            val valorTransfer = txtEditValorTransfer.text.toString()

            if (!TextUtils.isEmpty(valorTransfer)) {
                val valor = valorTransfer.toDoubleOrNull()
                if (valor != null) {
                    val transacao = Transacao(0, chavePix, valor)

                    mTransacaoViewModel.addTransacao(transacao)

                    (activity as MainActivity).atualizarSaldoTransacao(valor, "transferencia")

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