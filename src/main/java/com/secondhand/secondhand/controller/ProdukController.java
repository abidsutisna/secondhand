package com.secondhand.secondhand.controller;


import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.ProdukDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.dto.SearchDTO;
import com.secondhand.secondhand.models.entities.Category;
import com.secondhand.secondhand.models.entities.Image;
import com.secondhand.secondhand.models.entities.Penawaran;
import com.secondhand.secondhand.models.entities.Produk;
import com.secondhand.secondhand.services.CategoryService;
import com.secondhand.secondhand.services.CloudinaryStorageService;
import com.secondhand.secondhand.services.ImageService;
import com.secondhand.secondhand.services.PenawaranService;
import com.secondhand.secondhand.services.ProdukService;
import com.secondhand.secondhand.services.UserService;
import com.secondhand.secondhand.utils.StatusProdukEnum;

@RestController
@RequestMapping("/produk")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdukController {
    @Autowired
    private ProdukService produkService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Autowired
    private PenawaranService penawaranService;

    //menambahkan Produk.
    @PostMapping( 
                  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                  produces = {MediaType.APPLICATION_JSON_VALUE})  
    public ResponseEntity<ResponseDTO<Produk>> addProduk(@ModelAttribute ProdukDTO produkDTO, Errors errors){

    ResponseDTO<Produk> responseDTO = new ResponseDTO<>();

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
    Produk produk = new Produk();

    produk.setProdukname(produkDTO.getProdukName());
    produk.setHargaProduk(produkDTO.getHargaProduk());
    produk.setCategoryId(produkDTO.getCategoryId());
    produk.setCategoryName(categoryService.getById(produkDTO.getCategoryId()).getCategoryName());
    produk.setDeskripsi(produkDTO.getDeskripsi());
    produk.setUserId(produkDTO.getUserId());
    produk.setHistoryId(produkDTO.getUserId());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(produkService.addProduk(produk));

    for(int i=0; i<produkDTO.getImage().size(); i++){

      if(i >= 4){
        responseDTO.setStatus(false);
        responseDTO.getMessage().add("postingan tidak boleh lebih dari 4");
        responseDTO.setPayload(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
      }
      
      Map<?,?> uploadImage = (Map<?,?>)cloudinaryStorageService.upload(produkDTO.getImage().get(i)).getData();
      
      Image image = new Image();
      image.setProdukId(produk.getProdukId());
      image.setImagelink(uploadImage.get("url").toString());
      imageService.addImage(image);
    }

    responseDTO.getMessage().add("Succes add produk");
    return ResponseEntity.ok(responseDTO);
  }
  //mengupdate Produk
  @PutMapping("/update")
  public ResponseEntity<ResponseDTO<Produk>> updateProduk (@RequestBody @Valid Produk produk , Errors errors) {  
    ResponseDTO<Produk> responseDTO = new ResponseDTO<>();

    //if error
    //produk
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
    produkService.updateProduk(produk);
    responseDTO.setPayload(produk);
    responseDTO.getMessage().add("Succes update produk");
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping("/statusProduk")
  public ResponseEntity<ResponseDTO<Produk>> updateStatusProduk (@RequestBody @Valid Produk produk , Errors errors) {  
    ResponseDTO<Produk> responseDTO = new ResponseDTO<>();

    //if error
    //produk
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
    // Produk produk = new Produk();

    // produk.setProdukId(produkDTO.getProdukId()); //isi nya postman

    produk.setProdukname(produkService.getById(produk.getProdukId()).getProdukname());
    produk.setHargaProduk(produkService.getById(produk.getProdukId()).getHargaProduk());
    produk.setCategoryId(produkService.getById(produk.getProdukId()).getCategoryId());
    produk.setDeskripsi(produkService.getById(produk.getProdukId()).getDeskripsi());
    produk.setImage(produkService.getById(produk.getProdukId()).getImage());
    produk.setHistoryId(produkService.getById(produk.getProdukId()).getHistoryId());
    produk.setUserId(produkService.getById(produk.getProdukId()).getUserId());
    produk.setPenawaran(produkService.getById(produk.getProdukId()).getPenawaran());

    //true false
    if(produk.getStatusTerjual().equals("true")){
      produk.setStatusProduk(StatusProdukEnum.TERJUAL);
      responseDTO.getMessage().add("Status Produk menjadi TERJUAL");
    } else {
      produk.setStatusProduk(StatusProdukEnum.PUBLISH);
      responseDTO.getMessage().add("Transaksi dibatalkan");
    }

    produkService.updateProduk(produk);
    responseDTO.setPayload(produk);
    return ResponseEntity.ok(responseDTO);
  }

  //mendapatkan semua Produk
  @GetMapping
  public List<Produk> getAllProduks(){
      return this.produkService.getAllProduk();
  }

  //mendapatkan produk by id
  @GetMapping("/{id}")
  public Produk getProdukById(@PathVariable Long id){
      return this.produkService.getById(id);
  }

  //delete Produk
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProdukById(@PathVariable Long id){
    try {
      produkService.deleteProdukById(id);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/search")
  public List<Produk> getProdukByName(@RequestBody SearchDTO searchDTO ){
      return this.produkService.findByProdukName(searchDTO.getSearchKey());
  } 

  @GetMapping("/category/{id}")
  public List<Produk> getProdukByCategory(@PathVariable Long id){
      return this.produkService.getProdukByCategory(id);
  }

  @GetMapping("/history/{id}")
  public List<Produk> getHistoryProduk(@PathVariable Long id){
      return this.produkService.getHistoryProduk(id);
  }

  @GetMapping("/wishlist/{id}")
  public List<Produk> getWishlist(@PathVariable Long id){
      return this.produkService.getwishList(id);
  }
}
