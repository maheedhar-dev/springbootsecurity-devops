package com.devops.springsecuritydevops.controller;

import com.devops.springsecuritydevops.aop.TimeEvaluator;
import com.devops.springsecuritydevops.entity.Country;
import com.devops.springsecuritydevops.model.CountryModel;
import com.devops.springsecuritydevops.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(  CountryService countryService) {
          this.countryService = countryService;
    }

    @GetMapping("/countries")
    @TimeEvaluator
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PostMapping("/country")
    @TimeEvaluator
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Country> saveCountry(@RequestBody CountryModel countryModel){
        Country country = countryService.saveCountry(countryModel);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<Country> getCountryById(@PathVariable(name = "countryId") Long countryId){
        Country country = countryService.getCountryById(countryId);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }
}
