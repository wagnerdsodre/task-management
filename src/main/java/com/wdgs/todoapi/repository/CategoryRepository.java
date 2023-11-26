package com.wdgs.todoapi.repository;

import com.wdgs.todoapi.models.entities.CategoryEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

  Optional<CategoryEntity> findByGuid(String guid);
  
  }
