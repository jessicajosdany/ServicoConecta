package com.jessica.servicosconecta

data class Prestador(
    val id: Int,
    val nome: String,
    val telefone: String,
    val avaliacao: Double,
    val servicos: List<String>
)