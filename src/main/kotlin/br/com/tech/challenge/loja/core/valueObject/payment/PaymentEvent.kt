package br.com.tech.challenge.loja.core.valueObject.payment

enum class PaymentEvent(val description: String) {
  CREATED("Pagamento criado"),
  DENIED("Pagamento negado"),
  APPROVED("Pagamento aprovado"),
}