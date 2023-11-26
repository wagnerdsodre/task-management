package com.wdgs.todoapi.repository;

import com.wdgs.todoapi.models.entities.CheckListItemEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckListRepository extends JpaRepository<CheckListItemEntity, Long> {

  Optional<CheckListItemEntity> findByGuid(String guid);
  List<CheckListItemEntity> findByDescriptionAndIsCompleted(String description, Boolean status);

  

}
