package br.com.tech.challenge.loja.core.domain.entity.order

import br.com.tech.challenge.loja.core.domain.valueObject.status.Status
import br.com.tech.challenge.loja.core.domain.entity.product.Product
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("pedido")
data class Order(
    @Id
    val id: String? = null,
    val products: List<Product>,
    val createdAt: LocalDateTime? = null,
    val preco: Double,
    val status: Status? = null,
)
