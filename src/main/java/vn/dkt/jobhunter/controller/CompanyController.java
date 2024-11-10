package vn.dkt.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.dkt.jobhunter.domain.Company;
import vn.dkt.jobhunter.service.CompanyService;

@RestController
public class CompanyController {
  private CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @PostMapping("/companies")
  public ResponseEntity<Company> createNewCompany(@RequestBody @Valid Company company) {
    Company newCompany = this.companyService.saveCompany(company);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
  }
}
