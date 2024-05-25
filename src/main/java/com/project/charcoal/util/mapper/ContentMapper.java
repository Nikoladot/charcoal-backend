package com.project.charcoal.util.mapper;

import com.project.charcoal.controller.dto.ContentRequestDTO;
import com.project.charcoal.controller.dto.ContentResponseDTO;
import com.project.charcoal.controller.dto.ImageResponseDTO;
import com.project.charcoal.model.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContentMapper {
    ContentResponseDTO contentToContentResponseDTO(Content content);

}
