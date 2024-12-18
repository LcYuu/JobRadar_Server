package com.job_portal.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job_portal.DTO.ExperienceDTO;
import com.job_portal.config.JwtProvider;
import com.job_portal.models.Experience;
import com.job_portal.models.UserAccount;
import com.job_portal.repository.ExperienceRepository;
import com.job_portal.repository.UserAccountRepository;
import com.job_portal.service.IExperienceService;
import com.social.exceptions.AllExceptions;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

	@Autowired
	ExperienceRepository experienceRepository;

	@Autowired
	IExperienceService experienceService;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@GetMapping("/get-all")
	public ResponseEntity<List<Experience>> getExperience() {
		List<Experience> experiences = experienceRepository.findAll();
		return new ResponseEntity<>(experiences, HttpStatus.OK);
	}

	@PostMapping("/create-experience")
	public ResponseEntity<String> createExperience(@RequestHeader("Authorization") String jwt,
			@RequestBody ExperienceDTO experienceDTO) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		Optional<UserAccount> user = userAccountRepository.findByEmail(email);

		boolean isCreated = experienceService.createExp(experienceDTO, user.get().getUserId());
		if (isCreated) {
			return new ResponseEntity<>("Experience created successfully.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed to create Experience.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-experience/{experienceId}")
	public ResponseEntity<String> updateExperience(
			@RequestHeader("Authorization") String jwt,
			@RequestBody ExperienceDTO experienceDTO,
			@PathVariable("experienceId") Integer experienceId) {
		try {
			String email = JwtProvider.getEmailFromJwtToken(jwt);
			Optional<UserAccount> user = userAccountRepository.findByEmail(email);

			if (user.isEmpty()) {
				return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			}

			Experience experience = new Experience();
			experience.setJobTitle(experienceDTO.getJobTitle());
			experience.setCompanyName(experienceDTO.getCompanyName());
			experience.setDescription(experienceDTO.getDescription());
			experience.setStartDate(experienceDTO.getStartDate());
			experience.setEndDate(experienceDTO.getEndDate());

			boolean isUpdated = experienceService.updateExp(
				experience, 
				experienceId,
				user.get().getUserId()
			);

			if (isUpdated) {
				return new ResponseEntity<>("Update Experience success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No changes made", HttpStatus.OK);
			}
		} catch (AllExceptions e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Đã xảy ra lỗi trong quá trình xử lý yêu cầu.");
		}
	}

	@DeleteMapping("/delete-experience/{experienceId}")
	public ResponseEntity<String> deleteUser(@PathVariable("experienceId") Integer experienceId) {
		try {
			boolean isDeleted = experienceService.deleteExp(experienceId);
			if (isDeleted) {
				return new ResponseEntity<>("Experience deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Experience deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/seeker")
	public ResponseEntity<Object> searchExpByUserId(@RequestHeader("Authorization") String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		Optional<UserAccount> user = userAccountRepository.findByEmail(email);
		try {
			List<Experience> exps = experienceService.searchExpByUserId(user.get().getUserId());
			return ResponseEntity.ok(exps);
		} catch (AllExceptions e) {
			// Trả về thông báo từ service
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			// Trả về thông báo lỗi chung
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Đã xảy ra lỗi trong quá trình xử lý yêu cầu.");
		}
	}
	@GetMapping("/profile-seeker")
	public ResponseEntity<Object> searchExperienceByUserId(@RequestParam UUID userId) {
		try {
			List<Experience> exps = experienceService.searchExpByUserId(userId);
			return ResponseEntity.ok(exps);
		} catch (AllExceptions e) {
			// Trả về thông báo từ service
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			// Trả về thông báo lỗi chung
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Đã xảy ra lỗi trong quá trình xử lý yêu cầu.");
		}
	}
}
