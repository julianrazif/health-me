package com.example.dss.datasource.service;

import com.example.dss.datasource.model.Pasien;
import com.example.dss.datasource.repository.PasienRepository;
import com.example.dss.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("pasienService")
public class PasienService {

  private final PasienRepository pasienRepository;

  @Async
  public CompletableFuture<ResponseDto> getAllPasienByNikStartsWithOrderByNik(String nik, int offset, int limit) {
    return CompletableFuture.completedFuture(
      new ResponseDto(
        StatusDto
          .builder()
          .code(200)
          .response("ok")
          .message("Example of success get data by nik")
          .build(),
        getAllPasienByNikStartsWithOrderByNikDtos(nik, offset, limit)
      )
    );
  }

  @Async
  public CompletableFuture<ResponseDto> getAllPasienByNameLikeOrderByName(String name, int offset, int limit) {
    return CompletableFuture.completedFuture(
      new ResponseDto(
        StatusDto
          .builder()
          .code(200)
          .response("ok")
          .message("Example of success get data by name")
          .build(),
        getAllPasienByNameLikeOrderByNameDtos(name, offset, limit)
      )
    );
  }

  @Async
  public CompletableFuture<ResponseDto> getAllPasienByNameContainsIgnoreCaseOrderById(String name, int offset, int limit) {
    return CompletableFuture.completedFuture(
      new ResponseDto(
        StatusDto
          .builder()
          .code(200)
          .response("ok")
          .message("Example of success get data by name")
          .build(),
        getAllPasienByNameContainsIgnoreCaseOrderByIdDtos(name, offset, limit)
      )
    );
  }

  @Async
  public CompletableFuture<ResponseDto> pasienByNik(String nik) {
    return CompletableFuture.completedFuture(
      new ResponseDto(
        StatusDto
          .builder()
          .code(200)
          .response("ok")
          .message("Example of success get data by nik")
          .build(),
        getDtoFromEntity(pasienRepository.getPasienByNik(nik))
      )
    );
  }

  @Async
  public CompletableFuture<ResponseDto> insertOrUpdatePasien(PasienDto data) {
    Pasien entity = new Pasien();
    entity.setId(data.getId());
    entity.setName(data.getName());
    entity.setSex(data.getSex());
    entity.setReligion(data.getReligion());
    entity.setPhone(data.getPhone());
    entity.setAddress(data.getAddress());
    entity.setNik(data.getNik());
    return CompletableFuture.completedFuture(
      new ResponseDto(
        StatusDto
          .builder()
          .code(201)
          .response("created")
          .message("Example of success insert data")
          .build(),
        getDtoFromEntity(insertOrUpdate(entity))
      )
    );
  }

  private Pasien insertOrUpdate(Pasien entity) {
    Assert.notNull(entity, "Entity must not be null!");
    Assert.notNull(entity.getName(), "Name must not be null!");
    Assert.notNull(entity.getNik(), "NIK must not be null!");

    Pasien pasien = pasienRepository.getPasienByNik(entity.getNik());
    if (pasien != null) {
      String sex = pasien.getSex();
      String religion = pasien.getReligion();
      String phone = pasien.getPhone();
      String address = pasien.getAddress();

      pasien.setName(entity.getName());
      pasien.setSex(entity.getSex() != null ? entity.getSex() : sex);
      pasien.setReligion(entity.getReligion() != null ? entity.getReligion() : religion);
      pasien.setPhone(entity.getPhone() != null ? entity.getPhone() : phone);
      pasien.setAddress(entity.getAddress() != null ? entity.getAddress() : address);

      return pasienRepository.save(pasien);
    }

    return pasienRepository.save(entity);
  }

  @Async
  public CompletableFuture<Long> deletePasienByNik(String nik) {
    return CompletableFuture.completedFuture(pasienRepository.deletePasienByNik(nik));
  }

  private List<ResultDto<DomainObject>> getAllPasienByNikStartsWithOrderByNikDtos(String nik, int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    return pasienRepository
      .getAllByNikStartsWithOrderByNik(nik, pageable)
      .parallelStream()
      .map(this::getDtoFromEntity)
      .collect(Collectors.toList());
  }

  private List<ResultDto<DomainObject>> getAllPasienByNameLikeOrderByNameDtos(String name, int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    return pasienRepository
      .getAllByNameLikeIgnoreCaseOrderByName("%" + name + "%", pageable)
      .parallelStream()
      .map(this::getDtoFromEntity)
      .collect(Collectors.toList());
  }

  private List<ResultDto<DomainObject>> getAllPasienByNameContainsIgnoreCaseOrderByIdDtos(String name, int offset, int limit) {
    Pageable pageable = PageRequest.of(offset, limit);
    return pasienRepository
      .getAllByNameContainsIgnoreCaseOrderById(name, pageable)
      .parallelStream()
      .map(this::getDtoFromEntity)
      .collect(Collectors.toList());
  }

  private ResultDto<DomainObject> getDtoFromEntity(Pasien entity) {
    return new ResultDto<>(
      PasienDto
        .builder()
        .id(entity.getId())
        .name(entity.getName())
        .sex(entity.getSex())
        .religion(entity.getReligion())
        .phone(entity.getPhone())
        .address(entity.getAddress())
        .nik(entity.getNik())
        .build()
    );
  }
}
