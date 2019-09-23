package com.itworx.restdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * @author john.refaat
 * @date 7/22/2019
 **/

@Getter @AllArgsConstructor
public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;
}
