package br.com.tech.challenge.loja.core.domain.dto.order

import br.com.tech.challenge.loja.core.domain.entity.order.Order
import java.time.LocalDateTime

data class OrderSummaryDTO(
    val id: String? = null,
    val productsDescriptions: List<String>?,
    val preco: Double? = null,
    val status: String?,
    val tempoEspera: LocalDateTime? = null,
    val acompanhamentoURL: String? = null,
) {
  companion object {
    fun fromOrderToSummary(order: Order) =
        OrderSummaryDTO(
            id = order.id,
            productsDescriptions = order.products.map { it.descricao },
            status = order.status?.description
        )
  }
}
