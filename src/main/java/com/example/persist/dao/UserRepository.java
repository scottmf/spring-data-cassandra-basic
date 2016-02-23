package com.example.persist.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.persist.shared.User;

@Repository
public interface UserRepository extends CassandraRepository<User> {
//public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query("select * from users where fname = ?0")
	User findByFirstName(String firstName);
}
