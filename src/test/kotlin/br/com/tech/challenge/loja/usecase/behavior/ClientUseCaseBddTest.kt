package br.com.tech.challenge.loja.usecase.behavior

import br.com.tech.challenge.loja.core.entity.client.Client
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import br.com.tech.challenge.loja.usecase.ClientUseCase
import io.kotest.core.annotation.Tags
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk


@Tags("behavior")
class ClientUseCaseBddTest : BehaviorSpec({

  lateinit var clientGateway: IClientGateway
  lateinit var clientUseCase: ClientUseCase


  beforeTest {
    clientGateway = mockk<IClientGateway>()
    clientUseCase = ClientUseCase()
  }


  given("Um cliente ou Admin") {

    val client = Client("4545", "carla", "carla@teste.com")
    val cpf = "4545"

    `when`("Quando cadastra seus dados") {

      then("Salvamos a informação e retornamos a mesma") {
        every { clientGateway.save(client) } returns client

        val result = clientUseCase.saveClient(clientGateway, client)

        result.cpf shouldBe client.cpf
      }
    }


    `when`("Quando precisa trazer seus dados, informa seu cpf") {

      then("Buscamos seu registro por cpf e retornamos o mesmo") {
        every { clientGateway.findById(cpf) } returns client

        val result = clientUseCase.getClient(clientGateway, cpf)

        result?.nome shouldBe client.nome
      }
    }


    `when`("Quando precisa trazer seus dados, informa seu cpf") {

      then("Buscamos seu registro por cpf e retornamos o mesmo") {
        every { clientGateway.findById(cpf) } returns client

        val result = clientUseCase.getClient(clientGateway, cpf)

        result?.nome shouldBe client.nome
      }
    }


    `when`("Precisa listar clientes") {

      then("Buscamos todos os registros e retornamos a lista") {
        every { clientGateway.findAll() } returns listOf(client)

        shouldNotBeNull {
          clientUseCase.getClients(clientGateway)
        }
      }
    }

  }
})