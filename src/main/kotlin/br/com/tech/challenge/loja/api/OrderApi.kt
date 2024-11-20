package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.controller.OrderController
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.interfaces.client.IOrderClient
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
@Validated
class OrderApi(
  private val orderClient: IOrderClient,
  private val productGateway: IProductGateway,
  private val orderController: OrderController,
) {

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun getOrder(@PathVariable id: String) = orderController.getOrder(orderClient, id)


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createOrder(@RequestBody products: OrderDTO) =
    orderController.createOrder(orderClient, productGateway, products)

}