package com.itworx.restdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author john.refaat
 * @date 7/22/2019
 **/

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;


  public ResourceNotFoundException(String message) {
    super(message);
  }

}
