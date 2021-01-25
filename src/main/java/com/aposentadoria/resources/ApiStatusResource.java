package com.aposentadoria.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/status")
public class ApiStatusResource {

    @ApiOperation(value = "Retorna o status da API")
    @GetMapping
    public ResponseEntity<String> status() {
        return ResponseEntity.ok().body("Status: OK");
    }

}
