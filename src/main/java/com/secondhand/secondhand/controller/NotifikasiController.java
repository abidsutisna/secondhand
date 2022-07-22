package com.secondhand.secondhand.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.models.entities.NotifikasiBid;
import com.secondhand.secondhand.services.NotifikasiSevice;


@RestController 
@RequestMapping("/notifikasi")
@CrossOrigin(origins = "*", maxAge = 3600)
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

  @GetMapping("/{id}")
  public List<NotifikasiBid> getWishlist(@PathVariable Long id){
      return this.notifikasiService.getNotifikasiByUserId(id);
  }


}
