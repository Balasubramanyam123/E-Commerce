package org.niit.vehicleapp.repository;

import org.niit.vehicleapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends MongoRepository<User,String> {
}
