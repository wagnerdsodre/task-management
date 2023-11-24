package com.wdgs.todoapi.repository;

import com.wdgs.todoapi.models.entities.CategoryEntity;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {

  Optional<CategoryEntity> findByGuid(String guid);

}
