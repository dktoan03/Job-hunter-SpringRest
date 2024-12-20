package vn.dkt.jobhunter.domain;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import vn.dkt.jobhunter.util.SecurityUtil;

@Table(name = "companies")
@Entity
@Getter
@Setter
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank(message = "can't be blank")
  private String name;

  @Column(columnDefinition = "MEDIUMTEXT")
  private String description;

  private String address;

  private String logo;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
  private Instant createdAt;

  private Instant updatedAt;

  private String createdBy;

  private String updatedBy;

  @PrePersist
  public void handleBeforeCreate() {
    this.createdAt = Instant.now();
    this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() == true ? SecurityUtil.getCurrentUserLogin().get()
        : "";
  }

  @PreUpdate
  public void handleBeforeUpdate() {
    this.updatedAt = Instant.now();
    this.updatedBy = SecurityUtil.getCurrentUserLogin().isPresent() == true ? SecurityUtil.getCurrentUserLogin().get()
        : "";
  }
}
