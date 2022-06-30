package com.secondhand.secondhand.services;
import org.springframework.web.multipart.MultipartFile;

import com.secondhand.secondhand.result.DataResult;

public interface CloudinaryStorageService {
    DataResult<?> upload(MultipartFile multipartFile);
    DataResult<?> delete(String publicIdOfImage);
}