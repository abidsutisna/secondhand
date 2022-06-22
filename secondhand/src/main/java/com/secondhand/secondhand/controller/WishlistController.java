package com.secondhand.secondhand.controller;

import javax.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.ImageDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.dto.WishlistDTO;
import com.secondhand.secondhand.models.entities.Wishlist;
import com.secondhand.secondhand.services.WishlistService;

@RestController 
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    //menambahkan Wishlist.
    @PostMapping    
    public ResponseEntity<ResponseDTO<Wishlist>> addWishlist(@RequestBody @Valid WishlistDTO wishlistDTO, Errors errors){

    ResponseDTO<Wishlist> responseDTO = new ResponseDTO<>();

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
    Wishlist Wishlist = new Wishlist();

    image.setImagelink(imageDTO.getImagelink());
    image.setProdukId(imageDTO.getProdukId());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(wishlistService.addWishlist(wishlist));
    responseDTO.getMessage().add("Succes add Wishlist");
    return ResponseEntity.ok(responseDTO);
  }
  //mengupdate Wishlist
  @PutMapping("/update")
  public ResponseEntity<ResponseDTO<Wishlist>> updateImage (@RequestBody @Valid Wishlist wishlist , Errors errors) {  
    ResponseDTO<Wishlist> responseDTO = new ResponseDTO<>();

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
    wishlistService.updateWishlist(wishlist);
    responseDTO.setPayload(wishlist);
    responseDTO.getMessage().add("Succes update Wishlist");
    return ResponseEntity.ok(responseDTO);
  }

  //mendapatkan semua Wishlist
  @GetMapping
  public List<Image> getAllImages(){
      return this.wishlistService.getAllWishlist();
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
