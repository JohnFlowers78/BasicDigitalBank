package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.meusite.basicdigitalbank.R
import br.com.meusite.basicdigitalbank.data.CaixinhaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CaixnhasFragment : Fragment() {

    private lateinit var rvCaixinhas: RecyclerView
    private lateinit var caixinhaAdapter: CaixinhaAdapter
    private lateinit var mCaixinhaViewModel: CaixinhaViewModel
    private lateinit var btnAddCaixinha: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_caixnhas, container, false)

        // Inicializando elementos
        rvCaixinhas = view.findViewById(R.id.rvCaixinhas)
        btnAddCaixinha = view.findViewById(R.id.btnAddCaixinha)

        setupRecyclerView()
        setupViewModel()
        setupAddButton()

        return view
    }

    private fun setupRecyclerView() {
        rvCaixinhas.layoutManager = GridLayoutManager(requireContext(), 2)
        caixinhaAdapter = CaixinhaAdapter(emptyList()) { caixinha ->
            // Navega para o fragmento de detalhes
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.CaixnhaContainer, DetalhesCaixinhaFragment.newInstance(caixinha.id))
//                .addToBackStack(null)
//                .commit()
            val detalhesFragment = DetalhesCaixinhaFragment.newInstance(caixinha.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.CaixnhaContainer, detalhesFragment)
                .addToBackStack(null)
                .commit()
        }
        rvCaixinhas.adapter = caixinhaAdapter
    }

    private fun setupViewModel() {
        mCaixinhaViewModel = ViewModelProvider(requireActivity()).get(CaixinhaViewModel::class.java)
        mCaixinhaViewModel.readAllData.observe(viewLifecycleOwner) { caixinhas ->
            caixinhaAdapter.updateData(caixinhas)
        }
    }

    private fun setupAddButton() {
        btnAddCaixinha.setOnClickListener {
            // Navega para o fragmento de adicionar caixinha
            parentFragmentManager.beginTransaction()
                .replace(R.id.CaixnhaContainer, AddCaixinhaFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
