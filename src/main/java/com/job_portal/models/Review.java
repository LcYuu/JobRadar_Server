package com.job_portal.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "review_id")
    private UUID reviewId;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    
    @Column(name ="is_anonymous", nullable = false)
    private boolean isAnonymous;
    
    @Lob
    @Column(name = "message", columnDefinition = "MEDIUMTEXT")
    private String message;

    @Column(name = "star", nullable = false)
    private Integer star;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Seeker seeker;
    
    @JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
	private Company company;

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.isAnonymous = anonymous;
	}

	

	
}
