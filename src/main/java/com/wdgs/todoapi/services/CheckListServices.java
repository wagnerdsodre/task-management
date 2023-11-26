package com.wdgs.todoapi.services;

import com.wdgs.todoapi.exception.ResourceNotFoundException;
import com.wdgs.todoapi.models.entities.CategoryEntity;
import com.wdgs.todoapi.models.entities.CheckListItemEntity;
import com.wdgs.todoapi.repository.CategoryRepository;
import com.wdgs.todoapi.repository.CheckListRepository;
import java.time.LocalDate;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class CheckListServices {

  private final CheckListRepository checkListRepository;
  private final CategoryRepository categoryRepository;


  public CheckListServices(CheckListRepository checkListRepository,
      CategoryRepository categoryRepository) {
    this.checkListRepository = checkListRepository;
    this.categoryRepository = categoryRepository;
  }

  public void ValidCheckList(String description, Boolean isCompleted, LocalDate finalLimit,
      String categoryGuid) {

    if (!(StringUtils.hasText(description))) {
      throw new IllegalArgumentException("description cannot be null or empty");
    }
    if (!(StringUtils.hasText(description))) {
      throw new IllegalArgumentException("description cannot be null or empty");
    }
    if (isCompleted == null) {
      throw new IllegalArgumentException("IsCompleted cannot be null");
    }
    if (finalLimit == null) {
      throw new IllegalArgumentException("finalLimit cannot be null");
    }
    if (categoryGuid == null) {
      throw new IllegalArgumentException("Category cannot be null");
    }
  }

  public CheckListItemEntity addNewCheckListItem(String description, Boolean isCompleted,
      LocalDate finalLimit, String categoryGuid) {
    ValidCheckList(description, isCompleted, finalLimit, categoryGuid);

    CheckListItemEntity checkListItemEntity = new CheckListItemEntity();
    CategoryEntity category = categoryRepository.findByGuid(categoryGuid)
        .orElseThrow(() -> new ResourceNotFoundException(categoryGuid));

    checkListItemEntity.setDescription(description);
    checkListItemEntity.setGuid(UUID.randomUUID().toString());
    checkListItemEntity.setIsCompleted(isCompleted);
    checkListItemEntity.setFinalLimit(finalLimit);
    checkListItemEntity.setCategory(category);

    log.debug("new ChecklistItem added [checkListItemEntity={} ]", checkListItemEntity);

    return checkListRepository.save(checkListItemEntity);
  }

  public Iterable<CheckListItemEntity> FindAllCheckListItem() {
    return checkListRepository.findAll();
  }

  public void DeleteChekListItem(String guid) {
    if (!(StringUtils.hasText(guid))) {
      throw new IllegalArgumentException("description cannot be null or empty");
    }
    CheckListItemEntity entity = checkListRepository.findByGuid(guid)
        .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    entity.setDesActive();
    log.debug("ChecklistItem was des activated [checkListItemEntity={} ]", entity.getGuid());
    checkListRepository.save(entity);
  }


  public CheckListItemEntity UpdateCheckListItem(String guid, String description,
      Boolean isCompleted, LocalDate finalLimit, String categoryGuid) {

    if (!StringUtils.hasText(guid)) {
      throw new IllegalArgumentException("guid cannot be null or empty");
    }

    CheckListItemEntity checkListItem = checkListRepository.findByGuid(guid)
        .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

    if (StringUtils.hasText(description)) {
      checkListItem.setDescription(description);
    }
    if (isCompleted != null) {
      checkListItem.setIsCompleted(isCompleted);
    }
    if (isCompleted != null) {
      checkListItem.setFinalLimit(finalLimit);
    }

    if (!StringUtils.hasText(categoryGuid)) {
      CategoryEntity category = categoryRepository.findByGuid(categoryGuid)
          .orElseThrow(() -> new ResourceNotFoundException(categoryGuid));
      checkListItem.setCategory(category);
    }

    return checkListRepository.save(checkListItem);
  }

  public CheckListItemEntity FindByCheckListItem(String guid) {
    if (!StringUtils.hasText(guid)) {
      throw new IllegalArgumentException("Guid cannot be null or empty");
    }
    return checkListRepository.findByGuid(guid)
        .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
  }


}
