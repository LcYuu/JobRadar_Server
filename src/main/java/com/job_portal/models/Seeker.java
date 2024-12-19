package com.job_portal.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "seeker_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seeker {
    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "address", length = 100)
    private String address;

    @ManyToOne
    @JoinColumn(name = "industry_id", nullable = true)
    private Industry industry;

    @Column(name = "gender", length = 100)
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "description", columnDefinition = "TEXT")
	private String description;

    @Column(name = "email_contact", length = 50)
    private String emailContact;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
    
    @ManyToMany(mappedBy = "follows")
    private List<Company> followedCompanies = new ArrayList<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "seeker_profile_skills",
        joinColumns = @JoinColumn(name = "seeker_user_id"), // Khóa ngoại liên kết tới Seeker
        inverseJoinColumns = @JoinColumn(name = "skills_skill_id") // Khóa ngoại liên kết tới Skills
    )
    private List<Skills> skills = new ArrayList<>();

    
    @OneToMany(mappedBy = "seeker", fetch = FetchType.EAGER, cascade = CascadeType.ALL , orphanRemoval = true)
    private List<SocialLink> socialLinks = new ArrayList<>();
}
