package com.wdgs.todoapi.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "checkListItems")
@EqualsAndHashCode(of = "id", callSuper = true)
@Table(indexes = {@Index(name = "IDX_GUID_CAT_LIST", columnList = "guid")})
public class CheckListItemEntity extends Base{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  private Boolean isCompleted;

  private LocalDate finalLimit;

  private LocalDate createdAt;

  @ManyToOne
  private CategoryEntity category;


}