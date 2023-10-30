package com.crud.demo.observations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    @Autowired
    private ObservationRepository observationRepository;

    @GetMapping
    public List<Observation> listObservations() {
        return observationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Observation getObservation(@PathVariable Long id) {  
        return observationRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Observation createObservation(@RequestBody Observation observation) {
        return observationRepository.save(observation);
    }

    @PutMapping("/{id}")
    public Observation updateObservation(@PathVariable Long id, @RequestBody Observation updatedObservation) {
        Observation observation = observationRepository.findById(id).orElse(null);
        if (observation != null) {
            // Update the observation fields
            observation.setType(updatedObservation.getType());
            observation.setDate(updatedObservation.getDate());
            observation.setPatient(updatedObservation.getPatient());
            observation.setValue(updatedObservation.getValue());
            observation.setUnit(updatedObservation.getUnit());

            return observationRepository.save(observation);
        } else {
            return null; // Handle not found
        }
    }

    @DeleteMapping("/{id}")
    public void deleteObservation(@PathVariable Long id) {
        observationRepository.deleteById(id);
    }
}