package com.example.dss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasienDto implements DomainObject {

  private Long id;
  private String name;
  private String sex;
  private String religion;
  private String phone;
  private String address;
  private String nik;
}
