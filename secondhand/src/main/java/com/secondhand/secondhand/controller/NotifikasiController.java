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
import com.secondhand.secondhand.dto.NotifikasiDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.NotifikasiBid;
import com.secondhand.secondhand.services.NotifikasiSevice;


@RestController 
@RequestMapping("/notifikasi")
public class NotifikasiController {
    @Autowired
    private NotifikasiSevice notifikasiService;

    //menambahkan Notifikasi.
    @PostMapping    
    public ResponseEntity<ResponseDTO<NotifikasiBid>> addNotifikasiBid(@RequestBody @Valid NotifikasiDTO notifikasiDTO, Errors errors){

    ResponseDTO<NotifikasiBid> responseDTO = new ResponseDTO<>();

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
    NotifikasiBid notifikasiBid = new NotifikasiBid();

    
    notifikasiBid.setProdukId(notifikasiDTO.getProdukId());
    notifikasiBid.setHargaPenawaran(notifikasiDTO.getHargaPenawaran());
    notifikasiBid.setUserId(notifikasiDTO.getUserId());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(notifikasiService.addNotifikasiBid(notifikasiBid));
    responseDTO.getMessage().add("Succes add Notifikasi");
    return ResponseEntity.ok(responseDTO);
  }
  //mengupdate Notifikasi
  @PutMapping("/update")
  public ResponseEntity<ResponseDTO<NotifikasiBid>> updateNotifikasiBid (@RequestBody @Valid NotifikasiBid notifikasiBid , Errors errors) {  
    ResponseDTO<NotifikasiBid> responseDTO = new ResponseDTO<>();

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
    notifikasiService.updateNotifikasiBid(notifikasiBid);
    responseDTO.setPayload(notifikasiBid);
    responseDTO.getMessage().add("Succes update Notifikasi");
    return ResponseEntity.ok(responseDTO);
  }

  //mendapatkan semua Notifikasi
  @GetMapping
  public List<NotifikasiBid> getAllNotifikasiBid(){
      return this.notifikasiService.getAllNotifikasiBid();
  }

  //mendapatkan Notifikasi by id
  @GetMapping("/{id}")
  public NotifikasiBid getNotifikasiById(@PathVariable Long id){
      return this.notifikasiService.getById(id);
  }

  //delete Notifikasi
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteNotifikasiBidById(@PathVariable Long id){
    try {
        notifikasiService.deleteNotifikasiBidById(id);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }
}
