package com.project.charcoal.controller.dto;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class ContentResponseDTO {

    private int contentId;
    private String topicTitle;
    private String subTitle;
    private String topicTextContent;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
    private String mediaURL;
}
