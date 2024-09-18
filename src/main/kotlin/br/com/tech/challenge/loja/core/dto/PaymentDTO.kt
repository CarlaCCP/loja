package br.com.tech.challenge.loja.core.dto

import br.com.tech.challenge.loja.core.valueObject.payment.PaymentEvent

data class PaymentDTO(
  val id: String,
  val event: PaymentEvent
)
