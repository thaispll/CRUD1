package br.com.codeschool.crud1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(
    var listaTarefas: List<Tarefa>,
    val cliqueDeletar: (String) -> Unit,
    val cliqueAtualizar: (Tarefa) -> Unit,
): RecyclerView.Adapter<TarefaAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_tarefa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}