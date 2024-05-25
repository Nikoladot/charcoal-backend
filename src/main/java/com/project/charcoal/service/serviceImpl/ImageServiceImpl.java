package com.project.charcoal.service.serviceImpl;

import com.project.charcoal.controller.dto.ImageResponseDTO;
import com.project.charcoal.model.Image;
import com.project.charcoal.repository.ImageRepository;
import com.project.charcoal.util.CloudinaryUtility;
import com.project.charcoal.util.dto.CloudinaryResponseDTO;
import com.project.charcoal.service.ImageService;
import com.project.charcoal.util.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final CloudinaryUtility cloudinaryUtility;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public ImageResponseDTO uploadImage(MultipartFile multipartFile) throws IOException {
        CloudinaryResponseDTO cloudinaryResponse = cloudinaryUtility.uploadOnCloudinary(multipartFile);

        Image imageToSave = buildImageFromCloudinaryResponse(cloudinaryResponse);

        return imageMapper.imageToImageResponseDTO(imageRepository.save(imageToSave));
    }

    @Override
    public List<ImageResponseDTO> uploadImages(MultipartFile[] images) throws IOException {
        List<Image> savedImages = new ArrayList<>();

        for (MultipartFile image : images) {
            CloudinaryResponseDTO cloudinaryResponse = cloudinaryUtility.uploadOnCloudinary(image);
            Image imageToSave = buildImageFromCloudinaryResponse(cloudinaryResponse);
            savedImages.add(imageRepository.save(imageToSave));
        }

        return savedImages.stream()
                .map(image -> imageMapper.imageToImageResponseDTO(image))
                .collect(Collectors.toList());
    }

    @Override
    public ImageResponseDTO getImage(Integer imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found with id: " + imageId));
        return imageMapper.imageToImageResponseDTO(image);
    }

    @Override
    public void deleteImageById(Integer imageId) throws Exception {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found with id: " + imageId));

        String publicId = image.getPublicId();
        cloudinaryUtility.deleteImageFromCloudinary(publicId);

        imageRepository.delete(image);
    }

    private Image buildImageFromCloudinaryResponse(CloudinaryResponseDTO CloudinaryResponseDTO) {

        return Image.builder()
                .url(CloudinaryResponseDTO.getUrl())
                .publicId(CloudinaryResponseDTO.getPublicId())
                .createdDate(ZonedDateTime.now())
                .build();
    }

    private List<Image> uploadImagesToCloudinary(MultipartFile[] images) throws IOException {
        List<Image> savedImages = new ArrayList<>();

        for (MultipartFile image : images) {
            CloudinaryResponseDTO cloudinaryResponse = cloudinaryUtility.uploadOnCloudinary(image);
            Image imageToSave = Image.builder()
                    .createdDate(ZonedDateTime.now())
                    .url(cloudinaryResponse.getUrl())
                    .publicId(cloudinaryResponse.getPublicId())
                    .build();
            savedImages.add(imageToSave);
        }

        return savedImages;
    }
}
