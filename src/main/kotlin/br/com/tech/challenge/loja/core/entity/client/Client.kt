package br.com.tech.challenge.loja.core.entity.client

import br.com.tech.challenge.loja.interfaces.db.IClient

data class Client(
    override val cpf: String? = null,
    override val nome: String? = null,
    override val email: String? = null
) : IClient