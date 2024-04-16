package org.atique.aws.dynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

/**
 * @author atiQue
 * @since 16-Apr-24 4:20 PM
 */

public class BikeTypeConverter implements DynamoDBTypeConverter<String, BikeType> {

    @Override
    public String convert(BikeType object) {
        return object.name();
    }

    @Override
    public BikeType unconvert(String object) {
        return BikeType.valueOf(object);
    }
}
