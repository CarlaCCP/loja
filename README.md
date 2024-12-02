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


## Evidência de cobertura com Jacoco
![Captura de Tela 2024-12-02 às 08 49 15](https://github.com/user-attachments/assets/40cf5040-888f-4d38-a09e-02ced1fe09cd)

## Arquitetura
![Captura de Tela 2024-12-01 às 19 34 14](https://github.com/user-attachments/assets/7a7995ea-df80-414a-809b-765d0db9739b)

