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
    Produk produk = new Produk();

    //dw
    produk.setProdukname(produkDTO.getProdukName());
    produk.setHargaProduk(produkDTO.getHargaProduk());
    produk.setDeskripsi(produkDTO.getDeskripsi());
    produk.setImage(produkDTO.getImage());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(produkService.addProduk(produk));
    responseDTO.getMessage().add("Succes add produk");
    return ResponseEntity.ok(responseDTO);
  }
    
}
