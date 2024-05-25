package com.project.charcoal.controller;

import com.project.charcoal.controller.dto.ContentRequestDTO;
import com.project.charcoal.controller.dto.ContentResponseDTO;
import com.project.charcoal.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<ContentResponseDTO> createContent(@RequestBody ContentRequestDTO contentRequest) {
        ContentResponseDTO createdContent = contentService.createContent(contentRequest);
        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> getContentById(@PathVariable int id) {
        ContentResponseDTO content = contentService.getContentById(id);
        return ResponseEntity.ok(content);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> updateContent(@PathVariable int id, @RequestBody ContentRequestDTO contentRequest) {
        ContentResponseDTO updatedContent = contentService.updateContent(id, contentRequest);
        return ResponseEntity.ok(updatedContent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable int id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContentResponseDTO>> getAllContents() {
        List<ContentResponseDTO> contents = contentService.getAllContents();
        return ResponseEntity.ok(contents);
    }
}
