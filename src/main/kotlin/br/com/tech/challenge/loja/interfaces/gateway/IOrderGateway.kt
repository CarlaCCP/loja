package br.com.tech.challenge.loja.interfaces.gateway

import br.com.tech.challenge.loja.core.entity.order.Order
import br.com.tech.challenge.loja.core.valueObject.status.Status
import com.mongodb.client.result.DeleteResult
import org.springframework.stereotype.Repository

@Repository
interface IOrderGateway {

  fun findAll(): List<Order>

  fun findFirst(): Order?

  fun findByStatus(status: Status): List<Order>

  fun findById(id: String): Order?

  fun save(order: Order): Order

  fun deleteById(id: String): DeleteResult


}