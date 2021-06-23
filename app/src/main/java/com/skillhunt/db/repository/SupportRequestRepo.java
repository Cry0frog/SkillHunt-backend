package com.skillhunt.db.repository;

import com.skillhunt.db.supportRequest.SupportRequestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupportRequestRepo extends CrudRepository<SupportRequestEntity, Long> {

    List<SupportRequestEntity> findAll();
}
