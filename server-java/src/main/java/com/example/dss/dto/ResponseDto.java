package com.example.dss.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class ResponseDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private StatusDto status;
  private Object result;

  public ResponseDto(StatusDto status, Object result) {
    this.status = status;
    this.result = result;
  }
}
