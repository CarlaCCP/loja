package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.controller.OrderController
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.core.dto.PaymentDTO
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient
import br.com.tech.challenge.loja.interfaces.gateway.IOrderGateway
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
@Validated
class OrderApi(
  private val orderGateway: IOrderGateway,
  private val productGateway: IProductGateway,
  private val orderController: OrderController,
  private val paymentClient: IPaymentWebhookClient
) {

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun getOrder(@PathVariable id: String) = orderController.getOrder(orderGateway, id)

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getOrders() = orderController.getOrders(orderGateway)

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createOrder(@RequestBody products: OrderDTO) =
    orderController.createOrder(orderGateway, productGateway, products, paymentClient)

  @GetMapping("/status/{status}")
  @ResponseStatus(HttpStatus.OK)
  fun getByStatus(@PathVariable status: String) = orderController.getOrderByStatus(orderGateway, status)

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun deleteOrder(@PathVariable id: String) = orderController.deleteOrder(orderGateway, id)

  @PostMapping("/pagamento")
  @ResponseStatus(HttpStatus.CREATED)
  fun updatePaymentOrder(@RequestBody paymentDTO: PaymentDTO) =
    orderController.updatePaymentOrder(orderGateway, paymentDTO)
}