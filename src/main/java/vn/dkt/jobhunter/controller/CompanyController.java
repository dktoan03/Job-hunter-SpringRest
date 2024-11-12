package vn.dkt.jobhunter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("/companies")
  public ResponseEntity<List<Company>> getAllCompanies() {
    List<Company> allCompanies = this.companyService.getCompanies();
    return ResponseEntity.status(HttpStatus.CREATED).body(allCompanies);
  }

  @PutMapping("/companies")
  public ResponseEntity<Company> updateCompany(@RequestBody @Valid Company company) {
    Company curCompany = this.companyService.updateCompany(company);
    return ResponseEntity.status(HttpStatus.OK).body(curCompany);
  }

  @DeleteMapping("companies/{id}")
  public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) {
    this.companyService.deleteCompany(id);
    return ResponseEntity.ok(null);
  }

}
