package com.secondhand.secondhand.result;

public class DataResult<T> {
    private T data;
    private boolean success;
    private String message;

    public DataResult(T data, boolean success){
        this.success = success;
        this.data = data;
    }
    
    public DataResult(T data, boolean success, String message){
        this.success = success;
        this.data = data;
        this.message= message;
    }


    public T getData(){
        return this.data;
    }
}