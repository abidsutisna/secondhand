package com.secondhand.secondhand.controller;

import javax.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.ImageDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.Image;
import com.secondhand.secondhand.services.ImageService;

@RestController 
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    //menambahkan Image.
    @PostMapping    
    public ResponseEntity<ResponseDTO<Image>> addImage(@RequestBody @Valid ImageDTO imageDTO, Errors errors){

    ResponseDTO<Image> responseDTO = new ResponseDTO<>();

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
    Image image = new Image();

    image.setImagelink(imageDTO.getImagelink());
    image.setProdukId(imageDTO.getProdukId());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(imageService.addImage(image));
    responseDTO.getMessage().add("Succes add Image");
    return ResponseEntity.ok(responseDTO);
  }
  //mengupdate Image
  @PutMapping("/update")
  public ResponseEntity<ResponseDTO<Image>> updateImage (@RequestBody @Valid Image image , Errors errors) {  
    ResponseDTO<Image> responseDTO = new ResponseDTO<>();

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
    imageService.updateImage(image);
    responseDTO.setPayload(image);
    responseDTO.getMessage().add("Succes update Image");
    return ResponseEntity.ok(responseDTO);
  }

  //mendapatkan semua Image
  @GetMapping
  public List<Image> getAllImages(){
      return this.imageService.getAllImage();
  }

  //mendapatkan Image by id
  @GetMapping("/{id}")
  public Image getImageById(@PathVariable Long id){
      return this.imageService.getById(id);
  }

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
