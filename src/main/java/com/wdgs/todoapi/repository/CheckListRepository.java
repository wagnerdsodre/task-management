package com.wdgs.todoapi.repository;

import com.wdgs.todoapi.models.entities.CheckListItemEntity;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CheckListRepository  extends PagingAndSortingRepository<CheckListItemEntity, Long> {
  Optional<CheckListItemEntity> findByGuid(String guid);


}
