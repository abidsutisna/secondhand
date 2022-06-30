package com.secondhand.secondhand.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.secondhand.secondhand.services.CloudinaryStorageService;
import com.secondhand.secondhand.services.CloudinaryStorageServiceImpl;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinaryAccount(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name","de0yidcs5",
            "api_key","415953523312214",
            "api_secret","Y5JMq_FNpPplA8Fmtn-zn3sGfnw"

        ));
    }

    @Bean
    public CloudinaryStorageService cloudinaryStorageService(){
        return new CloudinaryStorageServiceImpl(cloudinaryAccount());
    }
}