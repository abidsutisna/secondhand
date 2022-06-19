package com.secondhand.secondhand.controller;


import java.util.List;

import javax.validation.Valid;

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

import com.secondhand.secondhand.dto.ProdukDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.Produk;
import com.secondhand.secondhand.services.ProdukService;

@RestController
@RequestMapping("/produk")
public class ProdukController {
    @Autowired
    private ProdukService produkService;

    //menambahkan Produk.
    @PostMapping    
    public ResponseEntity<ResponseDTO<Produk>> addProduk(@RequestBody @Valid ProdukDTO produkDTO, Errors errors){

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
    produk.setCategories(produkDTO.getCategories());
    produk.setDeskripsi(produkDTO.getDeskripsi());
    produk.setImage(produkDTO.getImage());
    produk.setUserId(produkDTO.getUserId());
    produk.setHistoryId(produkDTO.getHistoryId());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(produkService.addProduk(produk));
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

  //mendapatkan semua Produk
  @GetMapping
  public List<Produk> getAllSchedule(){
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
    
}
