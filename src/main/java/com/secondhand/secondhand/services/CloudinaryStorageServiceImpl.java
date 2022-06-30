package com.secondhand.secondhand.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.secondhand.secondhand.result.DataResult;
import com.secondhand.secondhand.result.ErrorDataResult;
import com.secondhand.secondhand.result.SuccessDataResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryStorageServiceImpl implements CloudinaryStorageService{

    @Autowired
    private final Cloudinary cloudinary;

    @Override
    public DataResult<?> upload(MultipartFile multipartFile){
        
        try{
            Map<?, ?> uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());

            return new SuccessDataResult<>(uploadResult);

        } catch (IOException e){
            e.printStackTrace();
            return new ErrorDataResult<>();
        }
    }


    @Override
    public DataResult<?> delete(String publicIdOfImage) {
        return null;
    }
}