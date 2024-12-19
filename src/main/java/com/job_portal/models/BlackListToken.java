package com.job_portal.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blacklist_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlackListToken {
	@Id
    private String token;
    
    private LocalDateTime blacklistedAt;
}
