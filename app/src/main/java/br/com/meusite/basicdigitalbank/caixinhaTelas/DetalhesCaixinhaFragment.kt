package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.meusite.basicdigitalbank.MainActivity
import br.com.meusite.basicdigitalbank.R

class DetalhesCaixinhaFragment : Fragment() {

    private var caixinhaId: Int? = null
    private var imagemUri: String? = null // Adicionando a URI da imagem
    lateinit var btnDeleteCaixinha: Button
    lateinit var btnUpdtCaixinha: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            caixinhaId = it.getInt(ARG_CAIXINHA_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalhes_caixinha, container, false)

        // Exibir os detalhes da caixinha aqui
        val textViewNome = view.findViewById<TextView>(R.id.CaixinhaNometextView)
        val textViewSaldo = view.findViewById<TextView>(R.id.SaldoCaixinhaTextView)
        val imageView = view.findViewById<ImageView>(R.id.selectedImageViewUpd2)

        val botaoGuardar = view.findViewById<Button>(R.id.btnGuardarDin)
        botaoGuardar.setOnClickListener {
            val valorDeposito = 100.0
            (activity as MainActivity).atualizarSaldoTransacao(valorDeposito, "deposito")
            Toast.makeText(requireContext(), "Valor guardado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        val botaoResgatar = view.findViewById<Button>(R.id.btnResgatarDin)
        botaoResgatar.setOnClickListener {
            val valorResgate = 50.0
            (activity as MainActivity).atualizarSaldoTransacao(valorResgate, "transferencia")
            Toast.makeText(requireContext(), "Valor resgatado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        val btnDeleteCaixinha = view.findViewById<Button>(R.id.btnDeleteCaixinha)
        btnDeleteCaixinha.setOnClickListener {  }

        val btnUpdtCaixinha = view.findViewById<Button>(R.id.btnUpdtCaixinha)
        btnDeleteCaixinha.setOnClickListener {  }

        return view
    }

    companion object {
        private const val ARG_CAIXINHA_ID = "caixinha_id"

        @JvmStatic
        fun newInstance(caixinhaId: Int) =
            DetalhesCaixinhaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CAIXINHA_ID, caixinhaId)
                }
            }
    }
}