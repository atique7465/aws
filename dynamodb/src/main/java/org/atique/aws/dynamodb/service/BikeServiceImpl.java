package org.atique.aws.dynamodb.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import org.atique.aws.dynamodb.domain.Bike;
import org.atique.aws.dynamodb.domain.BikeType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author atiQue
 * @since 23'Feb 2024 at 8:40 PM
 */

@Service
public class BikeServiceImpl implements BikeService {

    private final DynamoDBMapper mapper;

    public BikeServiceImpl(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Bike> search(String company, BikeType type, BigDecimal maxPrice) {
        return mapper.scan(Bike.class, getScanExpression(company, type, maxPrice));
    }

    @Override
    public Bike get(String id) {
        return mapper.load(Bike.class, id);
    }

    @Override
    public Bike save(Bike bike) {
        mapper.save(bike);
        return bike;
    }

    @Override
    public Bike update(String id, Bike request) {

        Bike old = mapper.load(Bike.class, id);

        if (old == null) {
            throw new RuntimeException("No bike found with id: " + id);
        }

        old.setCompany(request.getCompany());
        old.setType(request.getType());
        old.setModel(request.getModel());
        old.setDescription(request.getDescription());
        old.setColors(request.getColors());
        old.setFeatures(request.getFeatures());
        old.setAvailableIn(request.getAvailableIn());
        old.setPrice(request.getPrice());

        mapper.save(old);

        return old;
    }

    @Override
    public void delete(String id) {

        Bike bike = mapper.load(Bike.class, id);

        if (bike == null) {
            throw new RuntimeException("No bike found with id: " + id);
        }

        mapper.delete(bike);
    }

    private static DynamoDBScanExpression getScanExpression(String company, BikeType type, BigDecimal maxPrice) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        Map<String, Condition> scanFilter = new HashMap<>();
        if (company != null && !company.isEmpty()) {
            Condition companyCondition = new Condition()
                    .withComparisonOperator("EQ")
                    .withAttributeValueList(new AttributeValue(company));
            scanFilter.put("company", companyCondition);
        }
        if (type != null) {
            Condition typeCondition = new Condition()
                    .withComparisonOperator("EQ")
                    .withAttributeValueList(new AttributeValue(type.toString()));
            scanFilter.put("type", typeCondition);
        }
        if (maxPrice != null) {
            Condition priceCondition = new Condition()
                    .withComparisonOperator("LE")
                    .withAttributeValueList(new AttributeValue().withN(maxPrice.toString()));
            scanFilter.put("price", priceCondition);
        }

        scanExpression.setScanFilter(scanFilter);

        return scanExpression;
    }
}
