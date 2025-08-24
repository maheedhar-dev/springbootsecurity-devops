package com.devops.springsecuritydevops.controller;

import com.devops.springsecuritydevops.entity.Country;
import com.devops.springsecuritydevops.entity.State;
import com.devops.springsecuritydevops.model.StateModel;
import com.devops.springsecuritydevops.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/country/states/{countryId}")
    public ResponseEntity<List<State>> getStatesForCountry(@PathVariable(name = "countryId") Long countryId){
        List<State> states =   stateService.getAllStatesForCountry(countryId);
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @PostMapping("/country/states/{countryId}")
    public ResponseEntity<State> saveSatesForCountry(@PathVariable(name = "countryId") Long countryId, @RequestBody StateModel stateModel){
        State state =stateService.saveStateForCountry(stateModel,countryId);
        return new ResponseEntity<>(state,HttpStatus.OK);
    }

    @GetMapping("/state/{stateId}")
    public ResponseEntity<State> getStateForStateId(@PathVariable(name = "stateId") Long stateId){
        State state = stateService.getStateForStateId(stateId);
        return new ResponseEntity<>(state,HttpStatus.OK);
    }
}
