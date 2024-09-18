package br.com.tech.challenge.loja.interfaces.db

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("produto")
interface IProduct {
  @get:Id
  var id: String?
}