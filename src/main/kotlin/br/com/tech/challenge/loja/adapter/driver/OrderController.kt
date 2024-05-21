package br.com.tech.challenge.loja.adapter.driver

import br.com.tech.challenge.loja.core.applications.useCases.order.OrderService
import br.com.tech.challenge.loja.core.domain.dto.order.OrderRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
@Validated
class OrderController (

        private val orderService: OrderService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getOneOrder(@PathVariable id: String) = orderService.getOrder(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getOrder() = orderService.getAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody products: OrderRequest) = orderService.createOrder(products)

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    fun getByStatus(@PathVariable status: String) = orderService.getByStatus(status)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteOrder(@PathVariable id: String) = orderService.deleteOrder(id)
}