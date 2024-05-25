package com.project.charcoal.service;

import com.project.charcoal.controller.dto.ImageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    ImageResponseDTO uploadImage(MultipartFile multipartFil) throws IOException;
    List<ImageResponseDTO> uploadImages(MultipartFile[] multipartFile) throws IOException;
    ImageResponseDTO getImage(Integer imageId);
    void deleteImageById(Integer imageId) throws Exception;

}
