package org.niit.userauthenticationapp.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "vehicle-app", url ="localhost:8799")
public interface UserProxy {
    @PostMapping("/api/v1/vehicle-app/register")
    public abstract ResponseEntity<?> sendUserDtoToVehicleApp(@RequestBody UserDto userDto);
}
