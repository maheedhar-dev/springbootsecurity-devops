package com.devops.springsecuritydevops.service;

import com.devops.springsecuritydevops.entity.Country;
import com.devops.springsecuritydevops.entity.State;
import com.devops.springsecuritydevops.exception.BusinessException;
import com.devops.springsecuritydevops.model.StateModel;
import com.devops.springsecuritydevops.repo.CountryRepository;
import com.devops.springsecuritydevops.repo.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateService(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public List<State> getAllStatesForCountry(Long countryId){
        List<State> states = stateRepository.findAllByCountryId(countryId);
        if(CollectionUtils.isEmpty(states)){
            throw new BusinessException("No states available for the country with countryId:"+countryId);
        }
         return states;
    }

    public State saveStateForCountry(StateModel stateModel,Long countryId){
      Optional<Country> optionalCountry =  countryRepository.findById(countryId);
      Country country = optionalCountry.orElseThrow(()->new BusinessException("No Country found with the countryId:"+countryId));

      State state = new State();
      state.setName(stateModel.getName());
      state.setCode(stateModel.getCode());
      state.setCountry(country);

      return stateRepository.save(state);
    }

    public State getStateForStateId(Long stateId){
        Optional<State> optionalState = stateRepository.findById(stateId);
        return optionalState.orElseThrow(()->new BusinessException("No State Found With The stateId:"+stateId));
    }
}
