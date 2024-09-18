package br.com.tech.challenge.loja.core.entity.order

import br.com.tech.challenge.loja.core.valueObject.status.Status
import br.com.tech.challenge.loja.core.entity.product.Product
import br.com.tech.challenge.loja.interfaces.db.IOrder
import java.time.LocalDateTime

data class Order(
    override val id: String? = null,
    val products: List<Product>,
    val createdAt: LocalDateTime? = null,
    val preco: Double,
    val status: Status? = null,
) : IOrder
