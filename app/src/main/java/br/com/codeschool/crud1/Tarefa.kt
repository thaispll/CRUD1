package br.com.codeschool.crud1

data class Tarefa(
    var id: String = "",
    var titulo: String ="",
    var descricao: String = ""
){
    fun toMap(): Map<String, String>{
        return mapOf(
            "titulo" to titulo,
            "descricao" to descricao
        )
    }
}
