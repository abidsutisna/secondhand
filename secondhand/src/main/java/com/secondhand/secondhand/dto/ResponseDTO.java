package com.secondhand.secondhand.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> {
    
    private boolean status;
    private T payload;
    private List<String> message = new ArrayList<>();
}
