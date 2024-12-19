package com.job_portal.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "apply_job")
@IdClass(IdApplyJob.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJob {

    @Id
    @Column(name = "post_id", columnDefinition = "BINARY(16)")
    private UUID postId;

    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "path_CV", length = 100)
    private String pathCV;

    @Column(name = "apply_date")
    private LocalDateTime applyDate;

    @Column(name = "is_save")
    private boolean isSave;
    
    @Column(name = "full_name", length = 100)
    private String fullName;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "is_viewed", nullable = false)
    private boolean isViewed = false;
    
    @Lob
    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    private String description;

    // Quan hệ với JobPosts
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", insertable = false, updatable = false)
    private JobPost jobPost;

    // Quan hệ với SeekerProfile
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Seeker seeker;

	
}