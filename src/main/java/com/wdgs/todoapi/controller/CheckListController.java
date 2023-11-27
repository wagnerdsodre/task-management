package com.wdgs.todoapi.controller;

import com.wdgs.todoapi.dto.CheckListItemDTO;
import com.wdgs.todoapi.exception.ResourceNotFoundException;
import com.wdgs.todoapi.models.entities.CheckListItemEntity;
import com.wdgs.todoapi.services.CheckListServices;
import java.net.URI;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/checklistItems")
public class CheckListController {

  final CheckListServices service;

  public CheckListController(CheckListServices checkListServices) {
    this.service = checkListServices;
  }

  @GetMapping
  public ResponseEntity<List<CheckListItemDTO>> getAllCheckListItems() {
    CheckListItemEntity checkListItemEntity = null;
    List<CheckListItemDTO> result = StreamSupport.stream(
            service.FindAllCheckListItem().spliterator(), false)
        .map((entity) -> CheckListItemDTO.toDTO(checkListItemEntity)).toList();

    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity<CheckListItemDTO> PostChecklistItems(
      @RequestBody CheckListItemDTO checkListItemDTO) {

    if (!StringUtils.hasText(checkListItemDTO.getCategory().getGuid())) {
      throw new ResourceNotFoundException("Category Guid cannot be null or empty");
    }

    service.addNewCheckListItem(checkListItemDTO.getDescription(),
        checkListItemDTO.getIsCompleted(), checkListItemDTO.getPostDate(),
        checkListItemDTO.getCategory().getGuid());

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        .buildAndExpand(checkListItemDTO.getId()).toUri();

    return ResponseEntity.created(uri).build();

  }

  @PutMapping
  public ResponseEntity<CheckListItemEntity> UpdateCheckListItem(
      @RequestBody CheckListItemDTO DTO) {
    if (!StringUtils.hasLength(DTO.getGuid())) {
      throw new ResourceNotFoundException("Category Guid cannot be null or empty");
    }

    CheckListItemEntity entity = service.UpdateCheckListItem(DTO.getGuid(), DTO.getDescription(),
        DTO.getIsCompleted(), DTO.getPostDate(), DTO.getCategory().getGuid());

    return ResponseEntity.ok(entity);
  }

  @DeleteMapping
  public ResponseEntity<CheckListItemDTO> DeleteCheckListItem(String guid) {
    if (StringUtils.hasText(guid)) {
      throw new ResourceNotFoundException("Guid cannot be null or empty");
    }
    service.DeleteChekListItem(guid);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/{id}")
  public ResponseEntity<CheckListItemEntity> findByIdChecklistItem(@PathVariable String guid){
    CheckListItemEntity entity = service.FindByCheckListItem(guid);
    CheckListItemDTO dto = CheckListItemDTO.toDTO(entity);

    return ResponseEntity.ok(entity);
  }


}
