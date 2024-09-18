package br.com.tech.challenge.loja.adapter

import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.core.entity.order.Order
import br.com.tech.challenge.loja.core.valueObject.status.Status
import java.time.LocalDateTime

data class OrderGetAdapter(
  val id: String? = null,
  val products: List<ProductDTO>,
  val createdAt: LocalDateTime? = null,
  val preco: Double? = null,
  val status: String? = null
) {
  companion object {
    fun fromOrder(order: Order?) =
      order?.products?.let {
        OrderGetAdapter(
          order.id,
          it.map { product -> ProductDTO.fromProduct(product) },
          order.createdAt,
          order.preco,
          order.status?.description
        )
      }
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