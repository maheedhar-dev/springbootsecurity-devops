package com.devops.springsecuritydevops.service;

import com.devops.springsecuritydevops.entity.Country;
import com.devops.springsecuritydevops.entity.State;
import com.devops.springsecuritydevops.exception.BusinessException;
import com.devops.springsecuritydevops.model.CountryModel;
import com.devops.springsecuritydevops.repo.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries(){
        List<Country> counries = countryRepository.findAll();
        if(CollectionUtils.isEmpty(counries)){
            throw new BusinessException("Countries not available, Please save few companies");
        }
         return counries;
    }

    public Country getCountryById(Long countryId){
        Optional<Country> country = countryRepository.findById(countryId);
        return country.orElseThrow(()-> new BusinessException("No country exists by the id:"+countryId));
    }

    public Country saveCountry(CountryModel countryModel){
        Country country = new Country();
        country.setName(countryModel.getName());
        country.setCode(countryModel.getCode());
        List<State> states = countryModel.getStates().stream().map(stateModel -> {
            State state = new State();
            state.setName(stateModel.getName());
            state.setCode(stateModel.getCode());
            state.setCountry(country);
            return state;
        }).toList();
        country.setStates(states);
        return countryRepository.save(country);
    }
}
