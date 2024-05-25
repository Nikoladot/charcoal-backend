package com.project.charcoal.service;

import com.project.charcoal.controller.dto.ContentRequestDTO;
import com.project.charcoal.controller.dto.ContentResponseDTO;
import java.util.List;

public interface ContentService {
    ContentResponseDTO createContent(ContentRequestDTO contentRequest);
    ContentResponseDTO getContentById(int contentId);
    ContentResponseDTO updateContent(int contentId, ContentRequestDTO contentRequest);
    void deleteContent(int contentId);
    List<ContentResponseDTO> getAllContents();
}
