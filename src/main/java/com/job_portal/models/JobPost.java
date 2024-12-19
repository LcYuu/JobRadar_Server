package com.job_portal.models;

import lombok.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "post_id")
	private UUID postId;

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "expire_date", nullable = false)
	private LocalDateTime expireDate;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Lob
	@Column(name = "benefit", columnDefinition = "MEDIUMTEXT")
	private String benefit;

	@Lob
	@Column(name = "experience", columnDefinition = "MEDIUMTEXT")
	private String experience;

	@Column(name = "salary", nullable = false)
	private Long salary;

	@Lob
	@Column(name = "requirement", columnDefinition = "MEDIUMTEXT")
	private String requirement;

	@Column(name = "location", columnDefinition = "TEXT")
	private String location;

	@Column(name = "type_of_work", length = 50, nullable = false)
	private String typeOfWork;

	@Column(name = "position", length = 50, nullable = false)
	private String position;

	@Column(name = "status", length = 50, nullable = false)
	private String status;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // Thêm CascadeType.REMOVE
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // Thêm CascadeType.REMOVE
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(name = "is_approve", columnDefinition = "BIT(1)")
	private boolean isApprove;

	@Lob
	@Column(name = "nice_to_haves", columnDefinition = "MEDIUMTEXT")
	private String niceToHaves;

	@ManyToMany(cascade = CascadeType.REMOVE) 
	private List<Skills> skills = new ArrayList<>();

	@Column(name = "survey_email_sent")
	private Boolean surveyEmailSent = false;

}