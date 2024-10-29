package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.meusite.basicdigitalbank.data.Caixinha
import br.com.meusite.basicdigitalbank.data.CaixinhaViewModel
import br.com.meusite.basicdigitalbank.R
import com.google.android.material.textfield.TextInputEditText

class UpdateCaixinhaFragment : Fragment() {

    private lateinit var txtCaixinhaNome: TextInputEditText
    private lateinit var txtCaixinhaValor: TextInputEditText
    private lateinit var btnAtualizar: Button
    private lateinit var selectedImageView: ImageView
    private var selectedImageRes: Int = R.drawable.default_image // Imagem padrão
    private lateinit var mCaixinhaViewModel: CaixinhaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_caixinha, container, false)

        // Inicialização dos componentes
        txtCaixinhaNome = view.findViewById(R.id.txtEditNomeCaixinhaU) // ID modificado
        txtCaixinhaValor = view.findViewById(R.id.txtEditValorU) // ID modificado
        btnAtualizar = view.findViewById(R.id.btnAtualizarCaixinha) // ID modificado

        // Inicializa o ViewModel
        mCaixinhaViewModel = ViewModelProvider(this).get(CaixinhaViewModel::class.java)

        btnAtualizar.setOnClickListener {
            val nome = txtCaixinhaNome.text.toString()
            val valor = txtCaixinhaValor.text.toString()

            if (!TextUtils.isEmpty(valor)) {
                val valorDouble = valor.toDoubleOrNull()
                if (valorDouble != null) {
                    val imageSelected = selectedImageRes?.toString()
                    // Supondo que você tenha um método para obter a caixinha a ser atualizada
                    val caixinha = Caixinha(0, nome, valorDouble)
                    mCaixinhaViewModel.atualizarCaixinha(caixinha)  // Atualiza a caixinha
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

    fun voltar() {
        val intent = Intent(requireActivity(), CaixinhasActivity::class.java)
        startActivity(intent)
    }
}