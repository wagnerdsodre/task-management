package com.wdgs.todoapi.controller;

import com.wdgs.todoapi.dto.CategoryDTO;
import com.wdgs.todoapi.exception.ResourceNotFoundException;
import com.wdgs.todoapi.models.entities.CategoryEntity;
import com.wdgs.todoapi.services.CategoryService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService service;


  @GetMapping
  public ResponseEntity<List<CategoryDTO>> ListAllCategories() {
    List<CategoryDTO> list;
    list = StreamSupport.stream(service.FindAllCategories().spliterator(), false)
        .map(CategoryDTO::ToDTO).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(list);
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> PostCategory(@RequestBody CategoryDTO categoryDto) {
    CategoryEntity category = service.newCategory(categoryDto.getName());
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(category.getCategoryId()).toUri();

    return ResponseEntity.created(uri).body(CategoryDTO.ToDTO(category));
  }

  @PutMapping
  public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryEntity category) {
    CategoryEntity entity = service.updateCategory(category.getGuid(),
        category.getName());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(CategoryDTO.ToDTO(entity));
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteCategory(@RequestBody CategoryEntity category){
    service.DeleteCategory(category.getGuid());
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value ="{guid}")
  public ResponseEntity<CategoryDTO> FindCategoryID(@PathVariable String guid){

    if(!StringUtils.hasText(guid)){
      throw new ResourceNotFoundException("Guid cannot be null or empty");
    }
   CategoryEntity category = service.findByGuid(guid);
   return ResponseEntity.ok(CategoryDTO.ToDTO(category));



  }


}
