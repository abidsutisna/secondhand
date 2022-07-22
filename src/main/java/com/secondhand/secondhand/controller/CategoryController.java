package com.secondhand.secondhand.controller;

import javax.validation.Valid;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.CategoryDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.Category;
import com.secondhand.secondhand.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //menambahkan Category
    @PostMapping    
    public ResponseEntity<ResponseDTO<Category>> addCategory(@RequestBody @Valid CategoryDTO categoryDTO, Errors errors){

    ResponseDTO<Category> responseDTO = new ResponseDTO<>();

    if(errors.hasErrors()){
      for (ObjectError error : errors.getAllErrors()) {
          //add message ke response data
          responseDTO.getMessage().add(error.getDefaultMessage());
      }
      responseDTO.setStatus(false);
      //null karena terjadi error
      responseDTO.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
    Category category = new Category();

    category.setCategoryName(categoryDTO.getCategoryName());    
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(categoryService.addCategory(category));
    responseDTO.getMessage().add("Succes add Category");
    return ResponseEntity.ok(responseDTO);
  }
  //mengupdate Category
  @PutMapping("/update")
  public ResponseEntity<ResponseDTO<Category>> updateCategory (@RequestBody @Valid Category category , Errors errors) {  
    ResponseDTO<Category> responseDTO = new ResponseDTO<>();

    //if error
    if(errors.hasErrors()){
      for (ObjectError error : errors.getAllErrors()) {
          //add message ke response data
          responseDTO.getMessage().add(error.getDefaultMessage());
      }
      responseDTO.setStatus(false);
      //null karena terjadi error
      responseDTO.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }


    responseDTO.setStatus(true);
    categoryService.updateCategory(category);
    responseDTO.setPayload(category);
    responseDTO.getMessage().add("Succes update category");
    return ResponseEntity.ok(responseDTO);
  }

  //mendapatkan semua Category
  @GetMapping
  public List<Category> getAllCategory(){
      return this.categoryService.getAllCategories();
  }

  //mendapatkan Category by id
  @GetMapping("/{id}")
  public Category getCategoryById(@PathVariable Long id){
      return this.categoryService.getById(id);
  }

  //delete Category
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCategoryById(@PathVariable Long id){
    try {
        categoryService.deleteCategoryById(id);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }

}
