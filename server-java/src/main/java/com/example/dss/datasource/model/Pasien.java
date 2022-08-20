package com.example.dss.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Cacheable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pasien", indexes = {
  @Index(name = "index_nik", columnList = "nik")
})
@SQLDelete(sql = "update pasien set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
public class Pasien implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 75)
  private String name;

  @Column(name = "sex", length = 25)
  private String sex;

  @Column(name = "religion", length = 25)
  private String religion;

  @Column(name = "phone", length = 25)
  private String phone;

  @Column(name = "address_line")
  private String address;

  @Column(name = "nik", nullable = false, length = 16)
  private String nik;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;
}
