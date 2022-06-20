package com.secondhand.secondhand.services;

import com.secondhand.secondhand.models.entities.Image;

import java.util.List;

public interface ImageService {
    public Image addImage(Image newImage);
    void updateImage(Image image);
    void deleteImageById(Long id);
    Image getById(Long imageId);
    List<Image> getAllImage();
}
