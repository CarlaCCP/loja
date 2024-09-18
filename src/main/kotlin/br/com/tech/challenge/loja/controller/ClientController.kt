package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.adapter.ClientAdapter
import br.com.tech.challenge.loja.config.ClientConfig
import br.com.tech.challenge.loja.core.dto.ClientDTO
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import org.springframework.stereotype.Component

@Component
class ClientController(

  private val clientConfig: ClientConfig
) {
  fun saveClient(clientGateway: IClientGateway, client: ClientDTO): ClientDTO {
    val response = clientConfig.clientUseCase().saveClient(clientGateway, client.toClient())
    return ClientAdapter.fromClient(response)
  }

  fun getClient(clientGateway: IClientGateway, id: String): ClientDTO? {
    val response = clientConfig.clientUseCase().getClient(clientGateway, id)
    return ClientAdapter.fromClient(response)
  }

  fun getClients(clientGateway: IClientGateway): List<ClientDTO>? {
    val response = clientConfig.clientUseCase().getClients(clientGateway)
    return response.map { ClientAdapter.fromClient(it) }
  }

}