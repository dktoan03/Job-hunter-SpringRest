package vn.dkt.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.dkt.jobhunter.domain.Company;
import vn.dkt.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {
  private CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public Company saveCompany(Company company) {
    return this.companyRepository.save(company);
  }
}
