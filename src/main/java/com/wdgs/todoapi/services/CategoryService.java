package com.wdgs.todoapi.services;

import com.wdgs.todoapi.exception.ResourceNotFoundException;
import com.wdgs.todoapi.models.entities.CategoryEntity;
import com.wdgs.todoapi.repository.CategoryRepository;
import com.wdgs.todoapi.repository.CheckListRepository;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class CategoryService {

  final CheckListRepository checkListRepository;
  final CategoryRepository categoryRepository;

  public CategoryService(CheckListRepository checkListRepository,
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
    this.checkListRepository = checkListRepository;
  }

  private static void ifExistGuid(String guid) {
    if (guid == null) {
      throw new IllegalArgumentException(
          "Invalid parameters cause: guid cannot be null and name be empty");
    }
  }

  public Iterable<CategoryEntity> FindAllCategories() {
    return categoryRepository.findAll();
  }

  public CategoryEntity newCategory(String name) {
    if (!(StringUtils.hasText(name))) {
      throw new IllegalArgumentException("Category name cannot be null or empty");
    }
    CategoryEntity newCategory = new CategoryEntity();
    newCategory.setGuid(UUID.randomUUID().toString());
    newCategory.setName(name);
    log.debug("The new category with [ name = { }] was added", name);
    return newCategory;
  }

  public CategoryEntity updateCategory(String guid, String name) {
    ifExistGuid(name);
    CategoryEntity updateCategory = this.categoryRepository.findByGuid(guid)
        .orElseThrow(() -> new ResourceNotFoundException("Resource Not found"));

    updateCategory.setName(name);
    log.debug("This category was updated with[guid={} , name={}]", guid, name);
    return categoryRepository.save(updateCategory);

  }

  public void DeleteCategory(String guid) {
    ifExistGuid(guid);
    CategoryEntity deleteCategory = this.categoryRepository.findByGuid(guid)
        .orElseThrow(() -> new ResourceNotFoundException("Resource Not found"));
    deleteCategory.setActive(false);
    categoryRepository.save(deleteCategory);
  }

  public CategoryEntity findByGuid(String guid) {
    ifExistGuid(guid);
    return this.categoryRepository.findByGuid(guid)
        .orElseThrow(() -> new ResourceNotFoundException("Resource Not found"));

  }


}
