package com.groman.healthis.mysql_controller;

import com.groman.healthis.mysql_domain.Values;
import com.groman.healthis.mysql_dto.ValuesCreateRequest;
import com.groman.healthis.mysql_dto.ValuesDTO;
import com.groman.healthis.mysql_service.ValuesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/value")
public class ValuesController {

    ValuesService valuesService;

    public ValuesController(ValuesService valuesService) {this.valuesService = valuesService;}

    //GET
    @GetMapping("/{measurement_id}")
    public ResponseEntity<List<ValuesDTO>> getValues(@PathVariable Long measurement_id){
        return ResponseEntity.status(HttpStatus.FOUND).body(valuesService.getValues(measurement_id));
    }

    //POST
    @PostMapping("/{measurement_id}/")
    public ResponseEntity<Values> addValues(@PathVariable Long measurement_id, @RequestBody ValuesCreateRequest request){
        return ResponseEntity.status(HttpStatus.FOUND).body(valuesService.saveValues(measurement_id, request));
    }
}
