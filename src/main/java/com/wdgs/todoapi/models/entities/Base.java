package com.wdgs.todoapi.models.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = "guid")
public class Base {
  private String guid;

}

