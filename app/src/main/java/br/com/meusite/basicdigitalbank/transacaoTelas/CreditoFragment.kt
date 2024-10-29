package br.com.meusite.basicdigitalbank.transacaoTelas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import br.com.meusite.basicdigitalbank.R

class CreditoFragment : Fragment() {

    lateinit var btnPix: AppCompatImageButton
    lateinit var btnTransferir: AppCompatImageButton
    lateinit var btnDepositar: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla a view do fragmento
        val view = inflater.inflate(R.layout.fragment_credito, container, false)

        // Inicializa os botões usando a view inflada
        btnPix = view.findViewById(R.id.btnPix)
        btnTransferir = view.findViewById(R.id.btnTransferir)
        btnDepositar = view.findViewById(R.id.btnDepositar)

        // Define os ouvintes de clique
        btnPix.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerMain, TransferirFragment())
                .commit()
        }
        btnTransferir.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerMain, TransferirFragment())
                .commit()
        }
        btnDepositar.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.ContainerMain, DepositarFragment())
                .commit()
        }

        return view  // Retorna a view inflada
    }
}




