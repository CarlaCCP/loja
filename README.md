## Loja - Tech Challenge
Esta aplicação simula o fluxo de compra de uma loja.

## Fluxo de produto
- Ao iniciar a aplicação produtos são cadastrados no banco através de uma função com listener

## Fluxo de fake checkout

- A cada 2 minutos uma função schedulada é trigada para mover os status do pedido:
  Recebido -> Em preparação -> Pronto -> Finalizado


- A cada 2 minutos pedidos com status Finalizado são removidos do banco.

## Categorias de compra
- Lanche
- Sobremesa
- Acompanhamento
- Bebida

## Swagger
http://localhost:8080/swagger-ui/index.html

## Docker Compose
- Start da aplicação: docker-compose up -d
