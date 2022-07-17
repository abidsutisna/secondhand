package com.secondhand.secondhand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.secondhand.secondhand.services.NotifikasiSevice;


@RestController 
@RequestMapping("/notifikasi")
public class NotifikasiController {
    @Autowired
    private NotifikasiSevice notifikasiService;

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
