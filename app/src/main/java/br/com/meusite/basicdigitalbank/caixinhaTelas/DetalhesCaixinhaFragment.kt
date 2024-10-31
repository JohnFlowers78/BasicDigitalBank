package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.meusite.basicdigitalbank.R
import br.com.meusite.basicdigitalbank.data.CaixinhaViewModel

class DetalhesCaixinhaFragment : Fragment() {

    private var caixinhaId: Int? = null
    private lateinit var mcaixinhaViewModel: CaixinhaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            caixinhaId = it.getInt(ARG_CAIXINHA_ID)
        }
        mcaixinhaViewModel = ViewModelProvider(requireActivity()).get(CaixinhaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detalhes_caixinha, container, false)


        val textViewNome = view.findViewById<TextView>(R.id.CaixinhaNometextView)
        val textViewSaldo = view.findViewById<TextView>(R.id.SaldoCaixinhaTextView)

        caixinhaId?.let { id ->
            mcaixinhaViewModel.getCaixinhaById(id).observe(viewLifecycleOwner) { caixinha ->
                textViewNome.text = caixinha.nome
                textViewSaldo.text = "R$ %.2f".format(caixinha.saldo)
            }
        }

        val btnDeleteCaixinha = view.findViewById<Button>(R.id.btnDeleteCaixinha)
        btnDeleteCaixinha.setOnClickListener {
            caixinhaId?.let { id ->
                mcaixinhaViewModel.getCaixinhaById(id).observe(viewLifecycleOwner) { caixinha ->
                    if (caixinha != null) {
                        mcaixinhaViewModel.deletarCaixinha(caixinha)
                        // Retornar para a tela anterior
                        requireActivity().supportFragmentManager.popBackStack()
                    } else {
                        Toast.makeText(requireContext(), "Caixinha não encontrada", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        val btnUpdtCaixinha = view.findViewById<Button>(R.id.btnUpdtCaixinha)
        btnUpdtCaixinha.setOnClickListener {
            caixinhaId?.let { id ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.CaixnhaContainer, UpdateCaixinhaFragment.newInstance(id)) // Passando o ID
                    .addToBackStack(null)
                    .commit()
            } ?: run {
                Toast.makeText(requireContext(), "ID da Caixinha é nulo", Toast.LENGTH_SHORT).show()
            }
        }

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

//        val botaoGuardar = view.findViewById<Button>(R.id.btnGuardarDin)
//        botaoGuardar.setOnClickListener {
//            // Navegar para GuardarCaixinhaFragment
//            caixinhaId?.let { id ->
//                val guardarFragment = GuardarCaixinhaFragment.newInstance(id)
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.CaixnhaContainer, guardarFragment)
//                    .addToBackStack(null)
//                    .commit()
//            }
//        }
//
//        val botaoResgatar = view.findViewById<Button>(R.id.btnResgatarDin)
//        botaoResgatar.setOnClickListener {
//            // Navegar para ResgatarCaixinhaFragment
//            caixinhaId?.let { id ->
//                val resgatarFragment = ResgatarCaixinhaFragment.newInstance(id)
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.CaixnhaContainer, resgatarFragment)
//                    .addToBackStack(null)
//                    .commit()
//            }
//        }
