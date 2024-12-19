package com.job_portal.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "surveys")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private JobPost jobPost;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    
    private Integer hiredCount;
    private Integer candidateQuality;
    private String feedback;
    private LocalDateTime createdAt;
    private String surveyStatus;
    private Boolean emailSent;
    private LocalDateTime submittedAt;
}