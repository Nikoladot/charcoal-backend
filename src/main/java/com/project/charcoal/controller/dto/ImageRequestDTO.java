package com.project.charcoal.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDTO {

    private String url;
    private String publicId;
    private ZonedDateTime createdDate;
}
