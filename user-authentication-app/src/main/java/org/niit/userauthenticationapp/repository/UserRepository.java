package org.niit.userauthenticationapp.repository;

import org.niit.userauthenticationapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
