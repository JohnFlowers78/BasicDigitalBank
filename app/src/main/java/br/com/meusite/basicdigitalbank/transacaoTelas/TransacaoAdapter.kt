package br.com.meusite.basicdigitalbank.transacaoTelas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*    // Import para Adapter<> e ViewHolder()
import br.com.meusite.basicdigitalbank.data.Transacao
import br.com.meusite.basicdigitalbank.R

class TransacaoAdapter(private val lista: List<Transacao>) : Adapter<TransacaoAdapter.TransacaoViewHolder>() {

    inner class TransacaoViewHolder(itemView: View) : ViewHolder(itemView) {       // para gerenciar o layout de cada item exibido da RecyclerView
        val textDescricao: TextView = itemView.findViewById(R.id.textDescricao)          //Podemos dizer que o ViewHolder que recebe o "id" de cada componente para mapear os dados certo.
        val textValor: TextView = itemView.findViewById(R.id.textValor)
        val textData: TextView = itemView.findViewById(R.id.textData)
        val textHora: TextView = itemView.findViewById(R.id.textHora)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {  // método qeu infla o Layout XML para cada item
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_transacao, parent, false)  // Aqui estamos convertendo o layout XML item_transacao em uma View
        return TransacaoViewHolder(itemView)    // Cria e retorna uma instância do TransacaoViewHolder com a View inflada.
    }

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {    // Responsável por atribuir os valores de cada item do banco em suas devidas "View"s
        val transacao = lista[position]                                                 //  o "[position]" aqui é prova de que estamos fazendo isso para cada posição de registro do Database!
        holder.textDescricao.text = transacao.descricao
        holder.textValor.text = String.format("R$ %.2f", transacao.valor)
        holder.textData.text = transacao.data
        holder.textHora.text = transacao.hora
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}