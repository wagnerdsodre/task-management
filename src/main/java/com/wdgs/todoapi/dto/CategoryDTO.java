package com.wdgs.todoapi.dto;

import com.wdgs.todoapi.models.entities.CategoryEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDTO {

  private Long categoryId;

  private String name;
  private Boolean active;

  public static CategoryDTO ToDTO(CategoryEntity category) {
    return CategoryDTO.builder().categoryId(category.getCategoryId()).name(category.getName())
        .build();
  }


}
