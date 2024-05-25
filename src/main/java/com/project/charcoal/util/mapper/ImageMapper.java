package com.project.charcoal.util.mapper;

import com.project.charcoal.controller.dto.ImageResponseDTO;
import com.project.charcoal.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {
    ImageResponseDTO imageToImageResponseDTO(Image image);

}
