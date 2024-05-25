package com.project.charcoal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name = "content")
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentId;
    private String topicTitle;
    private String subTitle;
    private String topicTextContent;
    @Column(updatable = false)
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;

    private String mediaURL;
}
