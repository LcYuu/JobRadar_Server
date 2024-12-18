package com.job_portal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.job_portal.DTO.CompanyDTO;
import com.job_portal.models.Company;
import com.social.exceptions.AllExceptions;


public interface ICompanyService {
	public boolean deleteCompany(UUID companyId) throws AllExceptions;
	public boolean updateCompany(CompanyDTO companyDTO, UUID companyId) throws AllExceptions;
	public List<Company> searchCompaniesByName(String companyName) throws AllExceptions;
	public List<Company> searchCompaniesByCity(String cityName) throws AllExceptions;
	public List<Company> searchCompaniesByIndustry(String industryName) throws AllExceptions;
	public Company findCompanyById(UUID companyId) throws AllExceptions;
	public Map<String, Object> followCompany(UUID companyId, UUID userId) throws AllExceptions;
	public Integer getIndustryIdByCompanyId(UUID companyId);
	
}
