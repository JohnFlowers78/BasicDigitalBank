package br.com.meusite.basicdigitalbank.caixinhaTelas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.meusite.basicdigitalbank.data.Caixinha
import br.com.meusite.basicdigitalbank.R

class CaixinhaAdapter(
    private var caixinhas: List<Caixinha>,
    private val onItemClickListener: (Caixinha) -> Unit
) : RecyclerView.Adapter<CaixinhaAdapter.CaixinhaViewHolder>() {

    inner class CaixinhaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.descricaoTextView)
        val saldo: TextView = itemView.findViewById(R.id.saldoTextView)
        val imagem: ImageView = itemView.findViewById(R.id.imageviewCaixinha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaixinhaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_caixinha, parent, false)
        return CaixinhaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CaixinhaViewHolder, position: Int) {
        val caixinha = caixinhas[position]
        holder.nome.text = caixinha.nome
        holder.saldo.text = caixinha.saldo.toString()
        holder.imagem.setImageResource(R.drawable.default_image)
        holder.itemView.setOnClickListener {
            onItemClickListener(caixinha)
        }
    }

    override fun getItemCount(): Int {
        return caixinhas.size
    }

    fun updateData(newCaixinhas: List<Caixinha>) {
        caixinhas = newCaixinhas
        notifyDataSetChanged()
    }
}