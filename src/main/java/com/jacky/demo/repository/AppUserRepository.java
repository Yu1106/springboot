package com.jacky.demo.repository;

import com.jacky.demo.entity.app_user.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

    Optional<AppUser> findByEmailAddress(String email);

}
