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
import com.secondhand.secondhand.models.entities.NotifikasiBid;
import com.secondhand.secondhand.models.entities.Penawaran;
import com.secondhand.secondhand.models.entities.Produk;
import com.secondhand.secondhand.services.NotifikasiSevice;
import com.secondhand.secondhand.services.PenawaranService;
import com.secondhand.secondhand.services.ProdukService;
import com.secondhand.secondhand.utils.StatusProdukEnum;
import com.secondhand.secondhand.utils.StatusTawaranEnum;

@RestController 
@RequestMapping("/penawaran")
public class PenawaranController {
    @Autowired
    private PenawaranService penawaranService;

    @Autowired
    private ProdukService produkService;

    @Autowired
    private NotifikasiSevice notifikasiSevice;

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

    penawaran.setUserId(penawaranDTO.getUserIdPenawar());
    penawaran.setProdukId(penawaranDTO.getProdukId());

    if(produkService.getById(penawaranDTO.getProdukId()).getStatusProduk().equals(StatusProdukEnum.TERJUAL)){
      responseDTO.setStatus(true);
      responseDTO.setPayload(null);
      responseDTO.getMessage().add("Produk sudah habis terjual");
      return ResponseEntity.ok(responseDTO);
    }

    penawaran.setHargaPenawaran(penawaranDTO.getHargaPenawaran());

    NotifikasiBid notifikasibBid = new NotifikasiBid();
    notifikasibBid.setUserId(produkService.getById(penawaranDTO.getProdukId()).getUserId());
    notifikasiSevice.addNotifikasiBid(notifikasibBid);

    penawaran.setNotifikasiId(notifikasibBid.getNotifikasiId());
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(penawaranService.addPenawaran(penawaran));
    responseDTO.getMessage().add("Succes add Penawaran");
    return ResponseEntity.ok(responseDTO);
  }
 

  @PostMapping("/statusPenawaran")
  public ResponseEntity<ResponseDTO<Penawaran>> updateStatusPenawaran (@RequestBody @Valid Penawaran penawaran , Errors errors) {  
    ResponseDTO<Penawaran> responseDTO = new ResponseDTO<>();

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

    penawaran.setNotifikasiId(penawaranService.getById(penawaran.getPenawaranId()).getNotifikasiId());
    penawaran.setUserId(penawaranService.getById(penawaran.getPenawaranId()).getUserId());
    penawaran.setProdukId(penawaranService.getById(penawaran.getPenawaranId()).getProdukId());
    penawaran.setHargaPenawaran(penawaranService.getById(penawaran.getPenawaranId()).getHargaPenawaran());

    if(penawaran.getStatusTerima().equals("true")){
      penawaran.setStatusTawaran(StatusTawaranEnum.DEAL);
      responseDTO.getMessage().add("anda menerima tawaran");
    }else if(penawaran.getStatusTerima().equals("false")) {
      penawaran.setStatusTawaran(StatusTawaranEnum.DITOLAK);
      responseDTO.getMessage().add("anda menolak tawaran");
    } else {
      responseDTO.setStatus(false);
      responseDTO.setPayload(null);
      responseDTO.getMessage().add("true or false only");
    }

    penawaranService.updatePenawaran(penawaran);
    responseDTO.setPayload(penawaran);
    return ResponseEntity.ok(responseDTO);
  }

}
