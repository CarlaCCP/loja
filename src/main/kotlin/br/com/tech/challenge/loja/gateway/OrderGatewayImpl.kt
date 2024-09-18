package br.com.tech.challenge.loja.gateway

import br.com.tech.challenge.loja.interfaces.gateway.IOrderGateway
import br.com.tech.challenge.loja.core.entity.order.Order
import br.com.tech.challenge.loja.core.valueObject.status.Status
import com.mongodb.client.result.DeleteResult
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class OrderGatewayImpl(
  private val mongoTemplate: MongoTemplate
) : IOrderGateway {
  override fun findAll(): List<Order> {
    return mongoTemplate.find(
      Query.query(
        Criteria.where("status").ne(Status.FINALIZADO)
      ).with(Sort.by(Sort.Direction.DESC, "createAt")),
      Order::class.java
    )
  }

  override fun findFirst(): Order? {
    return mongoTemplate.findOne(
      Query.query(
        Criteria.where("status").exists(true)
      ).with(Sort.by(Sort.Direction.DESC, "createAt")),
      Order::class.java
    )
  }

  override fun findByStatus(status: Status): List<Order> {
    return mongoTemplate.find(
      Query.query(
        Criteria.where("status").isEqualTo(status)
      ).with(Sort.by(Sort.Direction.DESC, "createAt")),
      Order::class.java
    )
  }

  override fun findById(id: String): Order? {
    return mongoTemplate.findOne(
      Query.query(
        Criteria.where("id").isEqualTo(id)
      ), Order::class.java
    )
  }

  override fun save(order: Order): Order {
    return mongoTemplate.save(order)
  }

  override fun deleteById(id: String): DeleteResult {
    return mongoTemplate.remove(
      Query.query(
        Criteria.where("id").isEqualTo(id)
      ),
      Order::class.java
    )
  }

}