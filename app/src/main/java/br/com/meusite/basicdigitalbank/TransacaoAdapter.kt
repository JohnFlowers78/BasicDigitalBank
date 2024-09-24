package br.com.meusite.basicdigitalbank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*    // Import para Adapter<> e ViewHolder()

class TransacaoAdapter(private val lista: List<Transacao>) : Adapter<TransacaoAdapter.TransacaoViewHolder>() {

    inner class TransacaoViewHolder(itemView: View) : ViewHolder(itemView) {
        val textDescricao: TextView = itemView.findViewById(R.id.textDescricao)
        val textValor: TextView = itemView.findViewById(R.id.textValor)
        val textData: TextView = itemView.findViewById(R.id.textData)
        val textHora: TextView = itemView.findViewById(R.id.textHora)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_transacao, parent, false)
        return TransacaoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {
        val transacao = lista[position]
        holder.textDescricao.text = transacao.descricao
        holder.textValor.text = String.format("R$ %.2f", transacao.valor)
        holder.textData.text = transacao.data
        holder.textHora.text = transacao.hora
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
