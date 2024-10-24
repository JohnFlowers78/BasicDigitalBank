package br.com.meusite.basicdigitalbank.CaixinhaFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.meusite.basicdigitalbank.Data.Caixinha
import br.com.meusite.basicdigitalbank.R

class CaixinhaAdapter(
    private var caixinhas: List<Caixinha>,
    private val onItemClickListener: (Caixinha) -> Unit
) : RecyclerView.Adapter<CaixinhaAdapter.CaixinhaViewHolder>() {

    private var caixinhaList = emptyList<Caixinha>()

    class CaixinhaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.descricaoTextView)
        val saldo: TextView = itemView.findViewById(R.id.saldoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaixinhaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transacao, parent, false)
        return CaixinhaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CaixinhaViewHolder, position: Int) {
        val caixinha = caixinhas[position]
        holder.nome.text = caixinha.nome
        holder.saldo.text = caixinha.saldo.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener(caixinha)
        }
    }

    override fun getItemCount(): Int {
        return caixinhas.size
    }

    fun setData(caixinhas: List<Caixinha>){
        this.caixinhaList = caixinhas
        notifyDataSetChanged()
    }

    fun updateData(newCaixinhas: List<Caixinha>) {
        caixinhas = newCaixinhas
        notifyDataSetChanged()
    }
}