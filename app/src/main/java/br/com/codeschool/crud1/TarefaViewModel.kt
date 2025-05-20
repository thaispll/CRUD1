package br.com.codeschool.crud1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class TarefaViewModel: ViewModel() {
    private val db = Firebase.firestore
    private var _listaTarefas = MutableLiveData<List<Tarefa>>(emptyList())
    val listaTarefas: LiveData<List<Tarefa>> = _listaTarefas

    init {
        obterTarefas()

    }
    fun obterTarefas(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val resultado = db.collection("tarefas").get().await()
                val tarefas = resultado.documents.mapNotNull {  it.toObject((Tarefa::class.java)) }
                _listaTarefas.postValue(tarefas)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    fun adicionarTarefa(tarefa: Tarefa){
        tarefa.id = UUID.randomUUID().toString()
        viewModelScope.launch(Dispatchers.IO){
            try {
                db.collection("tarefas").document(tarefa.id).set(tarefa).await()
                _listaTarefas.postValue(_listaTarefas.value?.plus(tarefa))
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    fun atualizarTarefa(tarefa: Tarefa){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                db.collection("tarefas").document(tarefa.id).update(tarefa.toMap()).await()
                _listaTarefas.postValue(_listaTarefas.value?.map { if (it.id == tarefa.id) tarefa else it })
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    fun deletarTarefa(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                db.collection("tarefas").document(id).delete().await()
                _listaTarefas.postValue(_listaTarefas.value?.filter {it.id != id.})
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}