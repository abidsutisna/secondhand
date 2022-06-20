package com.secondhand.secondhand.services;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.secondhand.secondhand.models.entities.Image;
import com.secondhand.secondhand.models.repos.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image addImage(Image image) {
        return this.imageRepository.save(image);
    }

    @Override
    public void updateImage(Image image) {
        this.imageRepository.findById(image.getImageid()).get();
        this.imageRepository.save(image);
    }

    @Override
    public void deleteImageById(Long id) {
        this.imageRepository.deleteById(id);
    }

    @Override
    public Image getById(Long id) {
        return this.imageRepository.findById(id).get();
    }

    @Override
    public List<Image> getAllImage() {
        return this.imageRepository.findAll();
    }

    
}
