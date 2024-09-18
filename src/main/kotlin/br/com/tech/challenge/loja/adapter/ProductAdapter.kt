package br.com.tech.challenge.loja.adapter

import br.com.tech.challenge.loja.core.entity.product.Product

data class ProductAdapter(
  var id: String? = null,
  var categoria: String,
  var nome: String,
  var descricao: String,
  var preco: Double,
  var imagem: String
) {
  companion object {
    fun fromProduct(product: Product) =
      ProductAdapter(
        product.id,
        product.categoria.description,
        product.nome,
        product.descricao,
        product.preco,
        product.imagem
      )
  }
}
