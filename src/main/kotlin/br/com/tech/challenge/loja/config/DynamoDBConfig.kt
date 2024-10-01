package br.com.tech.challenge.loja.config

import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import br.com.tech.challenge.loja.interfaces.gateway.IOrderGateway
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import com.amazonaws.auth.InstanceProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableDynamoDBRepositories(
  basePackageClasses = [
    IClientGateway::class,
    IProductGateway::class,
    IOrderGateway::class
  ]
)
class DynamoDBConfig{
  @Bean
  fun amazonDynamoDB(): AmazonDynamoDB = AmazonDynamoDBClientBuilder
    .standard()
    .withRegion(Regions.US_EAST_1)
    .withCredentials(InstanceProfileCredentialsProvider(false))
    .build()

}