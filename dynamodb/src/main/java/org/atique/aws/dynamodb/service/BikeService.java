package org.atique.aws.dynamodb.service;


import org.atique.aws.dynamodb.domain.Bike;
import org.atique.aws.dynamodb.domain.BikeType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author atiQue
 * @since 23'Feb 2024 at 8:40 PM
 */

public interface BikeService {

    List<Bike> search(String company, BikeType type, BigDecimal maxPrice);

    Bike get(String id);

    Bike save(Bike bike);

    Bike update(String id, Bike request);

    void delete(String id);
}
