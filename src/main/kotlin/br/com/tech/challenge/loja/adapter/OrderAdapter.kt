package br.com.tech.challenge.loja.adapter

import br.com.tech.challenge.loja.core.entity.order.Order

data class OrderAdapter(
    val id: String? = null,
    val productsDescriptions: List<String>?,
    val preco: Double? = null,
    val status: String?,
    val tempoEspera: String? = null,
    val acompanhamentoURL: String? = null,
) {
  companion object {
    fun fromOrderToSummary(order: Order) =
      OrderAdapter(
        id = order.id!!,
        productsDescriptions = order.products.map { it.descricao },
        preco = order.preco,
        status = order.status!!.description,
        tempoEspera = "30 minutos",
        acompanhamentoURL = "/pedido/${order.id}"
      )
  }
}
