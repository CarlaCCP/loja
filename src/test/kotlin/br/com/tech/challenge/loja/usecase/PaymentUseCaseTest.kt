package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.core.dto.PaymentDTO
import br.com.tech.challenge.loja.core.valueObject.payment.PaymentEvent
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class PaymentUseCaseTest : FunSpec ({

  lateinit var paymentClient: IPaymentWebhookClient
  lateinit var paymentUseCase: PaymentUseCase

  beforeTest {
    paymentClient = mockk<IPaymentWebhookClient>()
    paymentUseCase = PaymentUseCase()
  }

  val paymentDTO = PaymentDTO(
    "1",
    PaymentEvent.APPROVED
  )

  test("Should get payment"){
    every { paymentClient.getPayment("1") } returns paymentDTO

    shouldNotBeNull {
      paymentUseCase.getPayment(paymentClient, "1")
    }
    paymentUseCase.getPayment(paymentClient, "1")
      ?.shouldBeEqual(PaymentEvent.APPROVED.description)
  }
})