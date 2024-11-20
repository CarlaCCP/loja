package br.com.tech.challenge.loja.interfaces.client

import br.com.tech.challenge.loja.adapter.OrderAdapter
import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.core.dto.OrderDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(url = "svc-pedido", value = "pedido")
//@FeignClient(url = "localhost:8082", value = "pedido")
interface IOrderClient {

  @RequestMapping(method = [RequestMethod.POST], value = ["/pedido"])
  fun createOrder(@RequestBody orderDTO: OrderDTO): OrderAdapter

  @RequestMapping(method = [RequestMethod.GET], value = ["/pedido/{id}"])
  fun getOrder(@PathVariable id: String): OrderGetAdapter
}