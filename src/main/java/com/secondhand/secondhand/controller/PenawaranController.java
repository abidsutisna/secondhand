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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.secondhand.secondhand.dto.PenawaranDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.Image;
import com.secondhand.secondhand.models.entities.NotifikasiBid;
import com.secondhand.secondhand.models.entities.Penawaran;
import com.secondhand.secondhand.models.entities.Produk;
import com.secondhand.secondhand.services.ImageService;
import com.secondhand.secondhand.services.NotifikasiSevice;
import com.secondhand.secondhand.services.PenawaranService;
import com.secondhand.secondhand.services.ProdukService;
import com.secondhand.secondhand.utils.StatusProdukEnum;
import com.secondhand.secondhand.utils.StatusTawaranEnum;
import com.secondhand.secondhand.utils.StatusWishlist;

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
    LocalDateTime time = LocalDateTime.now();
    DateTimeFormatter myFormatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedTime = time.format(myFormatTime);

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
    Image image = (Image)produkService.getById(penawaranDTO.getProdukId()).getImage().get(0);

    penawaran.setHargaPenawaran(penawaranDTO.getHargaPenawaran());
    penawaran.setHarga(produkService.getById(penawaranDTO.getProdukId()).getHargaProduk());
    penawaran.setImageUrl(image.getImagelink());
    penawaran.setProdukName(produkService.getById(penawaranDTO.getProdukId()).getProdukname());
    penawaran.setTime(formattedTime);
    penawaran.setMessageNotifikasi("Penawaran Produk");

    NotifikasiBid notifikasibBid = new NotifikasiBid();
    notifikasibBid.setUserId(produkService.getById(penawaranDTO.getProdukId()).getUserId());
    notifikasiSevice.addNotifikasiBid(notifikasibBid);
    penawaran.setNotifikasiId(notifikasibBid.getNotifikasiId());

    Produk produk = new Produk();
    produk.setProdukId(penawaranDTO.getProdukId());
    produk.setProdukname(produkService.getById(penawaranDTO.getProdukId()).getProdukname());
    produk.setHargaProduk(produkService.getById(penawaranDTO.getProdukId()).getHargaProduk());
    produk.setCategoryId(produkService.getById(penawaranDTO.getProdukId()).getCategoryId());
    produk.setCategoryName(produkService.getById(penawaranDTO.getProdukId()).getCategoryName());
    produk.setStatusTerjual(produkService.getById(penawaranDTO.getProdukId()).getStatusTerjual());
    produk.setStatusWishlist(StatusWishlist.YES);
    produk.setDeskripsi(produkService.getById(penawaranDTO.getProdukId()).getDeskripsi());
    produk.setImage(produkService.getById(penawaranDTO.getProdukId()).getImage());
    produk.setPenawaran(produkService.getById(penawaranDTO.getProdukId()).getPenawaran());
    produk.setHistoryId(produkService.getById(penawaranDTO.getProdukId()).getHistoryId());
    produk.setUserId(produkService.getById(penawaranDTO.getProdukId()).getUserId());
    produk.setStatusProduk(produkService.getById(penawaranDTO.getProdukId()).getStatusProduk());
    produkService.updateProduk(produk);
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(penawaranService.addPenawaran(penawaran));
    responseDTO.getMessage().add("Succes add Penawaran");
    return ResponseEntity.ok(responseDTO);
  }
 

  @PostMapping("/statusPenawaran")
  public ResponseEntity<ResponseDTO<Penawaran>> updateStatusPenawaran (@RequestBody @Valid Penawaran penawaran , Errors errors) {  
    ResponseDTO<Penawaran> responseDTO = new ResponseDTO<>();
    LocalDateTime time = LocalDateTime.now();
    DateTimeFormatter myFormatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedTime = time.format(myFormatTime);

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
    penawaran.setTime(formattedTime);
    penawaran.setImageUrl(penawaranService.getById(penawaran.getPenawaranId()).getImageUrl());
    penawaran.setProdukName(penawaranService.getById(penawaran.getPenawaranId()).getProdukName());
    penawaran.setHarga(penawaranService.getById(penawaran.getPenawaranId()).getHarga());

    if(penawaran.getStatusTerima().equals("true")){
      NotifikasiBid notifikasibBid = new NotifikasiBid();
      notifikasibBid.setUserId(penawaran.getUserId());
      notifikasiSevice.addNotifikasiBid(notifikasibBid);

      penawaran.setNotifikasiId(notifikasibBid.getNotifikasiId());
      penawaran.setMessageNotifikasi("Penjual menerima Tawaran");

      penawaran.setStatusTawaran(StatusTawaranEnum.DEAL);
      responseDTO.getMessage().add("anda menerima tawaran");
    }else if(penawaran.getStatusTerima().equals("false")) {
      NotifikasiBid notifikasibBid = new NotifikasiBid();
      notifikasibBid.setUserId(penawaran.getUserId());
      notifikasiSevice.addNotifikasiBid(notifikasibBid);

      penawaran.setNotifikasiId(notifikasibBid.getNotifikasiId());
      penawaran.setMessageNotifikasi("Penjual menolak Tawaran");

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
