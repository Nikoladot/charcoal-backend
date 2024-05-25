package com.project.charcoal.service.serviceImpl;

import com.project.charcoal.controller.dto.ContentRequestDTO;
import com.project.charcoal.controller.dto.ContentResponseDTO;
import com.project.charcoal.model.Content;
import com.project.charcoal.repository.ContentRepository;
import com.project.charcoal.service.ContentService;
import com.project.charcoal.util.mapper.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Override
    public ContentResponseDTO createContent(ContentRequestDTO contentRequest) {
        Content content = buildContentFromRequest(contentRequest);
        Content savedContent = contentRepository.save(content);
        return contentMapper.contentToContentResponseDTO(savedContent);
    }

    @Override
    public ContentResponseDTO getContentById(int contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("Content not found with id: " + contentId));
        return contentMapper.contentToContentResponseDTO(content);
    }

    @Override
    public ContentResponseDTO updateContent(int contentId, ContentRequestDTO contentRequest) {
        Content existingContent = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("Content not found with id: " + contentId));
        Content updatedContent = buildContentFromRequest(contentRequest);
        updatedContent.setContentId(contentId);
        Content savedContent = contentRepository.save(updatedContent);
        return contentMapper.contentToContentResponseDTO(savedContent);
    }

    @Override
    public void deleteContent(int contentId) {
        contentRepository.deleteById(contentId);
    }

    @Override
    public List<ContentResponseDTO> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        return contents.stream()
                .map(contentMapper::contentToContentResponseDTO)
                .collect(Collectors.toList());
    }


    // needs to be handled in future: pervent tryout to update createdDate field
    private Content buildContentFromRequest(ContentRequestDTO contentRequest) {
        return Content.builder()
                .topicTitle(contentRequest.getTopicTitle())
                .subTitle(contentRequest.getSubTitle())
                .topicTextContent(contentRequest.getTopicTextContent())
                .createdDate(ZonedDateTime.now())
                .updatedDate(ZonedDateTime.now())
                .mediaURL(contentRequest.getMediaURL())
                .build();
    }
}
