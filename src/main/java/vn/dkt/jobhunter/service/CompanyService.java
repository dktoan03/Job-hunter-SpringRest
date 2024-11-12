package vn.dkt.jobhunter.service;

import java.util.List;

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

  public List<Company> getCompanies() {
    return this.companyRepository.findAll();
  }

  public Company updateCompany(Company company) {
    if (!this.companyRepository.findById(company.getId()).isPresent())
      return null;
    Company curCompany = this.companyRepository.findById(company.getId()).get();
    curCompany.setAddress(company.getAddress());
    curCompany.setDescription(company.getDescription());
    curCompany.setLogo(company.getLogo());
    curCompany.setName(company.getName());

    return this.companyRepository.save(curCompany);
  }

  public void deleteCompany(long id) {
    this.companyRepository.deleteById(id);
  }
}
