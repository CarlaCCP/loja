package br.com.tech.challenge.loja.core.domain.dto.order

import br.com.tech.challenge.loja.core.domain.dto.product.ProductDTO

data class OrderRequest(
    val products: List<ProductDTO>
)
