package com.secondhand.secondhand.controller;

import javax.validation.Valid;
import java.util.List;
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

import com.secondhand.secondhand.dto.HistoryDTO;
import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.models.entities.History;
import com.secondhand.secondhand.services.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    //menambahkan Produk
    @PostMapping    
    public ResponseEntity<ResponseDTO<History>> addHistory(@RequestBody @Valid HistoryDTO historyDTO, Errors errors){

    ResponseDTO<History> responseDTO = new ResponseDTO<>();

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
    History history = new History();

    
    history.setUserId(historyDTO.getUserId());
    
    
    responseDTO.setStatus(true);
    responseDTO.setPayload(historyService.addHistory(history));
    responseDTO.getMessage().add("Succes add history");
    return ResponseEntity.ok(responseDTO);
  }
  //mengupdate Histrory
  @PutMapping("/update")
  public ResponseEntity<ResponseDTO<History>> updateHistory (@RequestBody @Valid History history , Errors errors) {  
    ResponseDTO<History> responseDTO = new ResponseDTO<>();

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
    historyService.updateHistory(history);
    responseDTO.setPayload(history);
    responseDTO.getMessage().add("Succes update history");
    return ResponseEntity.ok(responseDTO);
  }

  //mendapatkan semua History
  @GetMapping
  public List<History> getAllHistory(){
      return this.historyService.getAllHistory();
  }

  //mendapatkan History by id
  @GetMapping("/{id}")
  public History getHistoryById(@PathVariable Long id){
      return this.historyService.getById(id);
  }

  //delete History
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteHistoryById(@PathVariable Long id){
    try {
        historyService.deleteHistoryById(id);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }
    
    
}
