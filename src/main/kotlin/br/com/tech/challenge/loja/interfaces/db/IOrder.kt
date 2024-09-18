package br.com.tech.challenge.loja.interfaces.db

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("pedido")
interface IOrder {
  @get:Id
  val id: String?
}