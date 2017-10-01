package com.omotyliu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "NO SUCH USER")
public class CustomerNotFoundException extends RuntimeException{

}
