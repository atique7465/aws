package org.atique.aws.dynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author atiQue
 * @since 16'Feb 2024 at 2:27 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "bikes")
public class Bike {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    //  @DynamoDBIndexHashKey(attributeName = "company", globalSecondaryIndexName = "company-gsi")
    @DynamoDBAttribute
    private String company;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BikeTypeConverter.class)
    private BikeType type;

    @DynamoDBAttribute
    private String model;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private List<String> colors;

    @DynamoDBAttribute
    private List<String> features;

    @DynamoDBAttribute
    private List<String> availableIn;

    @DynamoDBAttribute
    private BigDecimal price;

    @DynamoDBVersionAttribute
    private Long version;

    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    private Date created;

    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.ALWAYS)
    private Date updated;
}
