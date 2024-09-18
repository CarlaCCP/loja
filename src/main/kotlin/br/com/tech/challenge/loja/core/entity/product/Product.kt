package br.com.tech.challenge.loja.core.entity.product

import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.interfaces.db.IProduct

data class Product(
  override var id: String? = null,
  var categoria: Category,
  var nome: String,
  var descricao: String,
  var preco: Double,
  var imagem: String
) : IProduct {

  companion object {
    fun buildStaticProducts() =
      listOf(
        buildProduct1(),
        buildProduct2(),
        buildProduct3(),
        buildProduct4()
      )

    private fun buildProduct1() =
      Product(
        categoria = Category.LANCHE,
        nome = "Lanche 1",
        descricao = "Lanche 1 teste",
        preco = 30.0,
        imagem = "image.png"
      )

    private fun buildProduct2() =
      Product(
        categoria = Category.ACOMPANHAMENTO,
        nome = "Acompanhemento 1",
        descricao = "Acompanhamento 1 teste",
        preco = 15.0,
        imagem = "image.png"
      )

    private fun buildProduct3() =
      Product(
        categoria = Category.BEBIDA,
        nome = "Bebida 1",
        descricao = "Bebida 1 teste",
        preco = 5.0,
        imagem = "image.png"
      )

    private fun buildProduct4() =
      Product(
        categoria = Category.SOBREMESA,
        nome = "Sobremesa 1",
        descricao = "Sobremesa 1 teste",
        preco = 10.0,
        imagem = "image.png"
      )
  }


}