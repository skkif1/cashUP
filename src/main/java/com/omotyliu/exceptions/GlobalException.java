package com.omotyliu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "SERVICE NOT AVAILABLE TRY LATER")
public class GlobalException extends Exception{

}
