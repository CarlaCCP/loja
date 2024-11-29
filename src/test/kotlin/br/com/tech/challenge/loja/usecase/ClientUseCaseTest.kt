package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.core.entity.client.Client
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class ClientUseCaseTest : FunSpec ({

  lateinit var clientGateway: IClientGateway
  lateinit var clientUseCase: ClientUseCase

  beforeTest {
    clientGateway = mockk<IClientGateway>()
    clientUseCase = ClientUseCase()
  }

  val client = Client("4545", "carla", "carla@teste.com")

  test("Should get client by cpf") {
    every { clientGateway.findById("12") } returns client
    shouldNotBeNull {
      clientUseCase.getClient(clientGateway, "12")
    }
  }

  test("Should save client") {
    every { clientGateway.save(client) } returns client
    shouldNotBeNull {
      clientUseCase.saveClient(clientGateway, client)
    }

  }

  test("Should get clients") {
    every { clientGateway.findAll() } returns listOf(client)
    shouldNotBeNull {
      clientUseCase.getClients(clientGateway)
    }
   val result =  clientUseCase.getClients(clientGateway)
    result.shouldContainExactly(client)
  }

})