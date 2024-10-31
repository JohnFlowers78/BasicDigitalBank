package br.com.meusite.basicdigitalbank.caixinhaTelas

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

class UpdateCaixinhaFragment : Fragment() {

    private var caixinhaId: Int? = null
    private lateinit var txtCaixinhaNome: TextInputEditText
    private lateinit var txtCaixinhaValor: TextInputEditText
    private lateinit var btnAtualizar: Button
    private lateinit var mCaixinhaViewModel: CaixinhaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            caixinhaId = it.getInt(ARG_CAIXINHA_ID)
        }
        mCaixinhaViewModel = ViewModelProvider(requireActivity()).get(CaixinhaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_caixinha, container, false)

        // Inicialização dos componentes
        txtCaixinhaNome = view.findViewById(R.id.txtEditNomeCaixinhaU) // ID modificado
        txtCaixinhaValor = view.findViewById(R.id.txtEditValorU) // ID modificado
        btnAtualizar = view.findViewById(R.id.btnAtualizarCaixinha) // ID modificado

        // Preenche os campos com os dados da caixinha
        caixinhaId?.let { id ->
            mCaixinhaViewModel.getCaixinhaById(id).observe(viewLifecycleOwner) { caixinha ->
                txtCaixinhaNome.setText(caixinha.nome)
                txtCaixinhaValor.setText(caixinha.saldo.toString())
            }
        }

        btnAtualizar.setOnClickListener {
            val nome = txtCaixinhaNome.text.toString()
            val valor = txtCaixinhaValor.text.toString()

            if (!TextUtils.isEmpty(valor)) {
                val valorDouble = valor.toDoubleOrNull()
                if (valorDouble != null && caixinhaId != null) {
                    // Atualiza a caixinha com o ID correto
                    val caixinha = Caixinha(caixinhaId!!, nome, valorDouble)
                    mCaixinhaViewModel.atualizarCaixinha(caixinha)
                    Toast.makeText(requireContext(), "Caixinha Atualizada!", Toast.LENGTH_LONG).show()
                    voltar()
                } else {
                    Toast.makeText(requireContext(), "Valor inválido! Por favor, insira um número válido.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Erro ao Atualizar. Preencha todos os campos.", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun voltar() {
        // Volta para o fragmento anterior (CaixinhasFragment)
        parentFragmentManager.popBackStack()
    }

    companion object {
        private const val ARG_CAIXINHA_ID = "caixinha_id"

        @JvmStatic
        fun newInstance(caixinhaId: Int) =
            UpdateCaixinhaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CAIXINHA_ID, caixinhaId)
                }
            }
    }
}
