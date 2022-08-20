package com.example.dss.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class ResultDto<T> implements Serializable {

  private T data;

  public ResultDto(T data) {
    this.data = data;
  }
}
