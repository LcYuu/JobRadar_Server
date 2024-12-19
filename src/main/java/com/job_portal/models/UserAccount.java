package com.job_portal.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccount {

	private static final String DEFAULT_AVATAR_URL = "https://res.cloudinary.com/ddqygrb0g/image/upload/v1721653511/default-avatar_lxvn0y.jpg";

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_id")
    private UUID userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @Column(name = "is_active", columnDefinition = "BIT(1)")
    private boolean isActive;

    @Column(name = "user_name", length = 500, nullable = false)
    private String userName;

    @Column(name = "avatar", length = 200)
    private String avatar;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 100, nullable = true) // Thay đổi nullable từ false thành true
    private String password;

    @Column(name = "create_date", nullable = true)
    private LocalDateTime createDate;
    
    @Column(name = "last_login", nullable = true)
    private LocalDateTime lastLogin;
    
    private String otp;
	private LocalDateTime otpGeneratedTime;
	
	@Column(name = "provider", nullable = false)
    private String provider;  // Trường provider để phân biệt cách đăng nhập
	
	@JsonIgnore
	@OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Seeker seeker;
	
	
//	@JsonProperty("company")
	@JsonIgnore
	@OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Company company;
	
	@OneToOne(mappedBy = "userAccount")
	private ForgotPassword forgotPassword;

	

	public UserAccount(UUID userId, UserType userType, boolean isActive, String userName, String avatar, String email,
			String password, LocalDateTime createDate, LocalDateTime lastLogin, String otp,
			LocalDateTime otpGeneratedTime, String provider, Seeker seeker, Company company) {
		this.userId = userId;
		this.userType = userType;
		this.isActive = isActive;
		this.userName = userName;
		this.avatar = avatar;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.lastLogin = lastLogin;
		this.otp = otp;
		this.otpGeneratedTime = otpGeneratedTime;
		this.provider = provider;
		this.seeker = seeker;
		this.company = company;
	}
	
	public String getProvider() {
		return provider;
	}



	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Seeker getSeeker() {
		return seeker;
	}

	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}

	// Constructors
    public UserAccount() {
    	this.avatar = DEFAULT_AVATAR_URL;
    	this.isActive = false; // Hoặc true tùy theo yêu cầu của bạn
    	this.provider = "LOCAL";
    }
    
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	// Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpGeneratedTime() {
		return otpGeneratedTime;
	}

	public void setOtpGeneratedTime(LocalDateTime otpGeneratedTime) {
		this.otpGeneratedTime = otpGeneratedTime;
	}
    
	@Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", isActive=" + isActive +
                ", otp='" + otp + '\'' +
                ", otpGeneratedTime=" + otpGeneratedTime +
                ", provider='" + provider + '\'' +
                ", userType=" + userType +
                '}';
    }
}