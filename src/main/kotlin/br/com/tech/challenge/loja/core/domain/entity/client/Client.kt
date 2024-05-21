package br.com.tech.challenge.loja.core.domain.entity.client

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("client")
data class Client(
    @Id
    val cpf: String? = null,
    val nome: String? = null,
    val email: String? = null
)