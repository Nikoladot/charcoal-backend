package com.project.charcoal.controller;

import com.project.charcoal.controller.dto.ImageResponseDTO;
import com.project.charcoal.util.dto.CloudinaryResponseDTO;
import com.project.charcoal.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/uploadImage")
    @ResponseBody
    public ImageResponseDTO uploadOnCloudinary(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded");
        }

        return imageService.uploadImage(multipartFile);
    }

    @PostMapping("/uploadImages")
    @ResponseBody
    public List<ImageResponseDTO> uploadMultipleOnCloudinary(@RequestParam("images") MultipartFile[] multipartFiles) throws IOException {
        if (multipartFiles == null || multipartFiles.length == 0) {
            throw new IllegalArgumentException("No files uploaded");
        }

        return imageService.uploadImages(multipartFiles);
    }

    @DeleteMapping("/deleteImage/{imageId}")
    @ResponseBody
    public String deleteImage(@PathVariable Integer imageId) {
        try {
            imageService.deleteImageById(imageId);
            return "Image deleted successfully";
        } catch (Exception e) {
             return "Error deleting image: " + e.getMessage();
        }
    }
}
