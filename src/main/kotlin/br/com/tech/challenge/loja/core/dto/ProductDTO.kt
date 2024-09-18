package br.com.tech.challenge.loja.core.dto

import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.core.entity.product.Product

data class ProductDTO(
    var id: String? = null,
    var categoria: String,
    var nome: String,
    var descricao: String,
    var preco: Double,
    var imagem: String
) {

  companion object {
    fun fromProduct(product: Product) =
        ProductDTO(
            product.id,
            product.categoria.description,
            product.nome,
            product.descricao,
            product.preco,
            product.imagem
        )
  }

  fun toProduct() =
      Product(
          id = id,
          categoria = Category.getByDescription(categoria),
          nome = nome,
          descricao = descricao,
          preco = preco,
          imagem = imagem

      )
}