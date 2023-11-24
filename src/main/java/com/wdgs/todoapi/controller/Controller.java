 package com.wdgs.todoapi.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/hello")
  public String hello(@RequestParam(required = false) String name){
    return "Hello"+ (StringUtils.hasText(name) ? name : "World!!!");
  }

}
