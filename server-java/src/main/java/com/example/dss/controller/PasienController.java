package com.example.dss.controller;

import com.example.dss.datasource.service.PasienService;
import com.example.dss.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pasien")
public class PasienController {

  private final PasienService pasienService;

  @GetMapping(value = "/{nik}/nik", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseDto getAllPasienByNikStartsWithOrderByNik(@PathVariable(name = "nik") String nik) {
    return pasienService.getAllPasienByNikStartsWithOrderByNik(nik, 0, 100).join();
  }

  @GetMapping(value = "/{name}/name", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseDto getAllPasienByNameContainsIgnoreCaseOrderById(@PathVariable(name = "name") String name) {
    return pasienService.getAllPasienByNameContainsIgnoreCaseOrderById(name, 0, 100).join();
  }
}
