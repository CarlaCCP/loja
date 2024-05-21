package br.com.tech.challenge.loja.core.domain.dto.order

import br.com.tech.challenge.loja.core.domain.dto.product.ProductDTO
import br.com.tech.challenge.loja.core.domain.entity.order.Order
import br.com.tech.challenge.loja.core.domain.valueObject.status.Status
import java.time.LocalDateTime

data class OrderDTO(
    val id: String? = null,
    val products: List<ProductDTO>,
    val createdAt: LocalDateTime? = null,
    val preco: Double? = null,
    val status: String? = null
) {
  companion object {
    fun fromOrder(order: Order) =
        OrderDTO(
            order.id,
            order.products.map { ProductDTO.fromProduct(it) },
            order.createdAt,
            order.preco,
            order.status?.description
        )
  }

  fun toPedido() =
      Order(
          products = products.map {
            it.toProduct()
          },
          preco = products.sumOf { it.preco },
          status = status?.let { Status.getByDescription(it) },
          createdAt = createdAt,
      )
}