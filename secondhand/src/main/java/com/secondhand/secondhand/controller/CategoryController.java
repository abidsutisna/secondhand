package com.secondhand.secondhand.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.CategoryDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.Category;
import com.secondhand.secondhand.services.CategoryService;

@RestController
@RequestMapping("/category")
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
    Category category= new Category();

    category.setCategoryName(categoryDTO.getCategoryName());
    category.setProdukId(categoryDTO.getProdukId());

    responseDTO.setStatus(true);
    responseDTO.setPayload(categoryService.addCategory(category));
    responseDTO.getMessage().add("Succes add category");
    return ResponseEntity.ok(responseDTO);

    }
}
