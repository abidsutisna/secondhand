package com.secondhand.secondhand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.services.ImageService;

@RestController 
@RequestMapping("/image")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {
    @Autowired
    private ImageService imageService;


  //delete Image
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteImageById(@PathVariable Long id){
    try {
        imageService.deleteImageById(id);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }

}
