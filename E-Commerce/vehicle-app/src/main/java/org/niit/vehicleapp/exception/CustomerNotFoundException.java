package org.niit.vehicleapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Customer is not found")
public class CustomerNotFoundException extends Exception{
}
