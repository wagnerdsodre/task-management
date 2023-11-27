package com.wdgs.todoapi.dto;

import com.wdgs.todoapi.models.entities.CategoryEntity;
import com.wdgs.todoapi.models.entities.CheckListItemEntity;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckListItemDTO {

  private Long id;
  private String guid;
  private String description;
  private Boolean isCompleted;
  private Boolean active;
  private LocalDate finalLimit;
  private LocalDate postDate;
  private CategoryEntity category;


  public static CheckListItemDTO toDTO(CheckListItemEntity checkListItemEntity) {

    return CheckListItemDTO.builder().guid(checkListItemEntity.getGuid())
        .id(checkListItemEntity.getCheckListItemId())
        .description(checkListItemEntity.getDescription())
        .isCompleted(checkListItemEntity.getIsCompleted()).active(checkListItemEntity.getActive())
        .finalLimit(checkListItemEntity.getFinalLimit()).postDate(checkListItemEntity.getPostDate())
        .category(checkListItemEntity.getCategory()).build();

  }


}
