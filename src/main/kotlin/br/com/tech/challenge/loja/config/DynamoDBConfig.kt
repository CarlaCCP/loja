package br.com.tech.challenge.loja.config

import br.com.tech.challenge.loja.config.properties.AwsProperties
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import br.com.tech.challenge.loja.interfaces.gateway.IOrderGateway
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.InstanceProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.s3.model.Region
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
class DynamoDBConfig(
  private val awsProperties: AwsProperties
) {
  @Bean
  fun amazonDynamoDB(): AmazonDynamoDB = AmazonDynamoDBClientBuilder
    .standard()
    .withRegion(Regions.US_EAST_1)
    .withCredentials(InstanceProfileCredentialsProvider(false))
    .build()

  private fun awsCredentialsProvider(): AWSCredentialsProvider =
    AWSStaticCredentialsProvider(awsCredentials())

  private fun awsCredentials(): AWSCredentials = BasicAWSCredentials(
    awsProperties.accessKey,
    awsProperties.secretKey
  )

}