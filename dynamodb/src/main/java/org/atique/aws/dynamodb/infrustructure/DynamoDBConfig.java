package org.atique.aws.dynamodb.infrustructure;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author atiQue
 * @since 16'Apr 2024 at 12:59 AM
 */

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Value("${amazon.aws.region}")
    private String region;

    @Value("${amazon.dynamodb.endpoint}")
    private String endpoint;

    @Value("${amazon.dynamodb.connection.maxsize}")
    private int connectionMaxSize;

    @Value("${amazon.dynamodb.connection.timeout}")
    private int connectionTimeOut;

    @Value("${amazon.dynamodb.connection.socket.timeout}")
    private int connectionSocketTimeOut;

    @Bean
    public ClientConfiguration dynamoDBClientConfig() {
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setConnectionTimeout(connectionTimeOut);
        clientConfig.setSocketTimeout(connectionSocketTimeOut);
        clientConfig.setMaxConnections(connectionMaxSize);
        return clientConfig;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(ClientConfiguration clientConfiguration) {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withClientConfiguration(clientConfiguration)
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
