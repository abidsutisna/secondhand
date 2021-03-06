package com.secondhand.secondhand.controller;

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
import java.util.List;
import com.secondhand.secondhand.dto.PenawaranDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.Penawaran;
import com.secondhand.secondhand.services.PenawaranService;

@RestController 
@RequestMapping("/penawaran")
public class PenawaranController {
    @Autowired
    private PenawaranService penawaranService;

    //menambahkan Penawaran
    @PostMapping    
    public ResponseEntity<ResponseDTO<Penawaran>> addPenawaran(@RequestBody @Valid PenawaranDTO penawaranDTO, Errors errors){

    ResponseDTO<Penawaran> responseDTO = new ResponseDTO<>();

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
    Penawaran penawaran = new Penawaran();

    penawaran.setNotifikasiId(penawaranDTO.getNotifikasiId());
    penawaran.setUserId(penawaranDTO.getUserId());
    penawaran.setProdukId(penawaranDTO.getProdukId());
    penawaran.setHargaPenawaran(penawaranDTO.getHargaPenawaran());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(penawaranService.addPenawaran(penawaran));
    responseDTO.getMessage().add("Succes add Penawaran");
    return ResponseEntity.ok(responseDTO);
  }
  //mengupdate Penawaran
  @PutMapping("/update")
  public ResponseEntity<ResponseDTO<Penawaran>> updatePenawaran (@RequestBody @Valid Penawaran penawaran , Errors errors) {  
    ResponseDTO<Penawaran> responseDTO = new ResponseDTO<>();

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
    penawaranService.updatePenawaran(penawaran);
    responseDTO.setPayload(penawaran);
    responseDTO.getMessage().add("Succes update Penawaran");
    return ResponseEntity.ok(responseDTO);
  }

  //mendapatkan semua Penawaran
  @GetMapping
  public List<Penawaran> getAllPenawaran(){
      return this.penawaranService.getAllPenawaran();
  }

  //mendapatkan Penawaran by id
  @GetMapping("/{id}")
  public Penawaran getPenawaranById(@PathVariable Long id){
      return this.penawaranService.getById(id);
  }

  //delete Penawaran
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePenawaranById(@PathVariable Long id){
    try {
        penawaranService.deletePenawaranById(id);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }
}
