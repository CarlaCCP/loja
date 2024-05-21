package br.com.tech.challenge.loja.core.applications.useCases.order

import br.com.tech.challenge.loja.core.domain.dto.order.OrderDTO
import br.com.tech.challenge.loja.core.domain.entity.order.Order
import br.com.tech.challenge.loja.core.domain.valueObject.status.Status
import br.com.tech.challenge.loja.core.applications.ports.OrderRepository
import br.com.tech.challenge.loja.core.applications.ports.ProductRepository
import br.com.tech.challenge.loja.core.domain.dto.order.OrderRequest
import br.com.tech.challenge.loja.core.domain.dto.order.OrderSummaryDTO
import br.com.tech.challenge.loja.core.domain.dto.order.OrderDTO.Companion.fromOrder
import mu.KotlinLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(

    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) {

  private val log = KotlinLogging.logger {}
  fun getOrder(id: String): OrderDTO? = orderRepository.findById(id)?.let { fromOrder(it) }

  fun getByStatus(status: String): List<Order>? =
      Status.getByDescription(status)?.let {
        orderRepository.findByStatus(it)
      }

  fun createOrder(orderRequest: OrderRequest): OrderSummaryDTO {
    orderRequest.products.map {
      if (productRepository.findById(it.id!!) == null)
        throw NotFoundException()
    }
    val order = orderRepository.save(
        OrderDTO(products = orderRequest.products)
            .toPedido()
            .copy(
                createdAt = LocalDateTime.now(),
                status = Status.RECEBIDO
            ))

    return OrderSummaryDTO(
        id = order.id!!,
        productsDescriptions = order.products.map { it.descricao },
        preco = order.preco,
        status = order.status!!.description,
        tempoEspera = order.createdAt!!.plusMinutes(30),
        acompanhamentoURL = "/pedido/${order.id}"
    )
  }

  fun deleteOrder(id: String) = orderRepository.deleteById(id)

  fun getAll() = orderRepository.findAll()

  @Scheduled(fixedDelay = 120000)
  fun updateStatus() {
    val order: Order? = orderRepository.findFirst()

    if (order != null) {
      orderRepository.save(order.copy(
          status = updateStatus(order.status)
      ))
      log.info { "Pedido ${order.id} foi atualizado para o status ${updateStatus(order.status)}" }
    }
  }

  @Scheduled(fixedRate = 120000)
  fun deleteOrder() {
    val listOrders: List<Order> = orderRepository.findByStatus(Status.FINALIZADO)

    if (listOrders.isNotEmpty()) {
      listOrders.map {
        orderRepository.deleteById(it.id!!)
        log.info { "Pedido ${it.id} finalizado removido da fila" }
      }
    }
  }


  private fun updateStatus(status: Status?): Status =
      when (status) {
        Status.RECEBIDO -> Status.EM_PREPARACAO
        Status.EM_PREPARACAO -> Status.PRONTO
        Status.PRONTO -> Status.FINALIZADO
        else -> Status.RECEBIDO
      }
}