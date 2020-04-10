package com.khramykh.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED)
public class EmailAlreadyUsedException extends RuntimeException{
}
