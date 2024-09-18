package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.core.entity.order.Order
import br.com.tech.challenge.loja.core.valueObject.status.Status
import br.com.tech.challenge.loja.interfaces.gateway.IOrderGateway
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.core.dto.PaymentDTO
import br.com.tech.challenge.loja.core.valueObject.payment.PaymentEvent
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient
import mu.KotlinLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.time.LocalDateTime

class OrderUseCase {

  private val log = KotlinLogging.logger {}
  fun getOrder(orderGateway: IOrderGateway, id: String): Order? = orderGateway.findById(id)

  fun getOrderByStatus(orderGateway: IOrderGateway, status: String): List<Order>? =
    Status.getByDescription(status)?.let {
      orderGateway.findByStatus(it)
    }

  fun createOrder(
    orderGateway: IOrderGateway,
    productRepository: IProductGateway,
    orderRequest: OrderDTO,
    paymentClient: IPaymentWebhookClient
  ): Order {
    orderRequest.products.map {
      if (productRepository.findById(it.id!!) == null)
        throw NotFoundException()
    }
    val order = orderGateway.save(
      OrderGetAdapter(products = orderRequest.products)
        .toPedido()
        .copy(
          createdAt = LocalDateTime.now(),
          status = Status.RECEBIDO
        )
    )
    val paymentResponse = paymentClient.createPayment(order.id!!)
    log.info { "Pagamento com status ${paymentResponse.name}" }
    return if (paymentResponse != PaymentEvent.CREATED) {
      orderGateway.save(
        OrderGetAdapter(products = orderRequest.products)
          .toPedido()
          .copy(
            createdAt = LocalDateTime.now(),
            status = Status.NEGADO
          )
      )
    } else {
      order
    }
  }

  fun deleteOrder(orderGateway: IOrderGateway, id: String) = orderGateway.deleteById(id)

  fun getOrders(orderGateway: IOrderGateway) = orderGateway.findAll()

  fun deleteOrderByStatus(orderGateway: IOrderGateway) {
    val listOrders: List<Order> = orderGateway.findByStatus(Status.FINALIZADO)

    if (listOrders.isNotEmpty()) {
      listOrders.map {
        orderGateway.deleteById(it.id!!)
        log.info { "Pedido ${it.id} finalizado removido da fila" }
      }
    }
  }

  fun updateOrderByStatus(orderGateway: IOrderGateway) {
    val order: Order? = orderGateway.findFirst()

    if (order != null) {
      orderGateway.save(
        order.copy(
          status = if (updateStatus(order.status) == null) order.status else updateStatus(order.status)
        )
      )
      log.info { "Pedido ${order.id} foi atualizado para o status ${updateStatus(order.status)}" }
    }
  }

  fun updatePaymentOrder(orderGateway: IOrderGateway, paymentDTO: PaymentDTO): PaymentDTO {
    val order = orderGateway.findById(paymentDTO.id)
    if (order != null && paymentDTO.event == PaymentEvent.APPROVED) {
      orderGateway.save(
        order.copy(
          status = Status.EM_PREPARACAO
        )
      )
      log.info { "Pagamento de pedido ${order.id} realizado com sucesso" }
    } else if (order != null && paymentDTO.event == PaymentEvent.DENIED) {
      orderGateway.save(
        order.copy(
          status = Status.NEGADO
        )
      )
      log.info { "Pagamento de pedido ${order.id} realizado com sucesso" }
    } else {
      if (order != null) {
        orderGateway.save(order.copy(status = Status.NEGADO))
        log.info { "Pagamento de pedido ${order.id} negado" }
      }
    }
    return paymentDTO
  }

  private fun updateStatus(status: Status?): Status? =
    when (status) {
      Status.EM_PREPARACAO -> Status.PRONTO
      Status.PRONTO -> Status.FINALIZADO
      else -> null
    }
}