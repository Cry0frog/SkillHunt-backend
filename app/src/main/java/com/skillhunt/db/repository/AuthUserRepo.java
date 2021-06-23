package com.skillhunt.db.repository;

import com.skillhunt.db.auth.AuthUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthUserRepo extends CrudRepository<AuthUserEntity, Long> {

  List<AuthUserEntity> findAll();
}