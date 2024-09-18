package br.com.tech.challenge.loja.interfaces.dto

import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.br.CPF

interface IClientDTO {
  @get:CPF(message = "Cpf inválido")
  val cpf: String?

  @get:Email(message = "Email invalido")
  val email: String?
}