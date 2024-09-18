package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.core.entity.client.Client
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway

class ClientUseCase {

  fun saveClient(clientGateway: IClientGateway, client: Client) = clientGateway.save(client)

  fun getClient(clientGateway: IClientGateway, id: String) = clientGateway.findById(id)

  fun getClients(clientGateway: IClientGateway) = clientGateway.findAll()
}