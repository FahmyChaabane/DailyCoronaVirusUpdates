package com.chaabane.virus.corona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController

public class CoronaController {

    @Autowired
    private CoronaService coronaService;


    @CrossOrigin(origins = "*")
    @GetMapping("/status")
    public ResponseEntity<CoronaResponse> getStatus(){
        CoronaResponse coronaResponse = new CoronaResponse();
        try {
            coronaResponse.setList(coronaService.fetchCoronaVirusData());
            coronaResponse.setTotateff(coronaResponse.getList().stream().mapToInt(e -> e.getLastTotalCases()).sum());
        } catch(IOException e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(coronaResponse, HttpStatus.OK);
    }

}
