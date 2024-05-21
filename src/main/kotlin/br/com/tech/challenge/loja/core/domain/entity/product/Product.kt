package br.com.tech.challenge.loja.core.domain.entity.product

import br.com.tech.challenge.loja.core.domain.valueObject.category.Category
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("produto")
data class Product (
    @Id
    var id: String? = null,
    var categoria: Category,
    var nome: String,
    var descricao: String,
    var preco: Double,
    var imagem: String
)