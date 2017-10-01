package com.omotyliu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "USER ALREADY EXIST")
public class UserAlreadyExistException extends CashUpException{

}
