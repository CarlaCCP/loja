package br.com.tech.challenge.loja.core.applications.ports

import br.com.tech.challenge.loja.core.domain.entity.order.Order
import br.com.tech.challenge.loja.core.domain.valueObject.status.Status
import com.mongodb.client.result.DeleteResult
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository {

  fun findAll(): List<Order>

  fun findFirst(): Order?

  fun findByStatus(status: Status): List<Order>

  fun findById(id: String): Order?

  fun save(order: Order): Order

  fun deleteById(id: String): DeleteResult


}