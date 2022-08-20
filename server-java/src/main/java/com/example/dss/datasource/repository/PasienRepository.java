package com.example.dss.datasource.repository;

import com.example.dss.datasource.model.Pasien;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface PasienRepository extends JpaRepository<Pasien, Integer> {

  @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 60)
  List<Pasien> getAllByNikStartsWithOrderByNik(@Param("nik") String nik, Pageable pageable);

  @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 60)
  List<Pasien> getAllByNameLikeIgnoreCaseOrderByName(@Param("name") String name, Pageable pageable);

  @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 60)
  List<Pasien> getAllByNameContainsIgnoreCaseOrderById(@Param("name") String name, Pageable pageable);

  @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 60)
  Pasien getPasienByNik(@Param("nik") String nik);

  @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 60)
  Long deletePasienByNik(@Param("nik") String nik);
}
