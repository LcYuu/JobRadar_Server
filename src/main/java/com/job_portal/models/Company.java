package com.job_portal.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	@Id
	@Column(name = "company_id")
	private UUID companyId;

	@Column(name = "company_name", length = 500)
	private String companyName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "industry_id")
	private Industry industry;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;

	@Column(name = "address", length = 200)
	private String address;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "logo", length = 200)
	private String logo;

	@Column(name = "contact", length = 15)
	private String contact;
	
	@Column(name = "tax_code", length = 30)
	private String taxCode;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "established_time", length = 50)
	private LocalDate establishedTime;

	@JsonIgnore
	@OneToMany
	private List<Review> reviews = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "company_follows", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "seeker_id"))
	private List<Seeker> follows = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<JobPost> jobPosts = new ArrayList<>();

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private UserAccount userAccount;

//	@JsonIgnore
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL , orphanRemoval = true)
	private List<ImageCompany> images = new ArrayList<>();

}
