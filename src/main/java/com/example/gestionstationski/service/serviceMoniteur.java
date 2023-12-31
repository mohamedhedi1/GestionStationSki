package com.example.gestionstationski.service;


import com.example.gestionstationski.entities.Cours;
import com.example.gestionstationski.entities.Moniteur;
import com.example.gestionstationski.repository.CoursRepository;
import com.example.gestionstationski.repository.MoniteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class serviceMoniteur implements  IserviceMoniteur{
@Autowired
    MoniteurRepository moniteurRepo;
@Autowired
    CoursRepository coursRepository;

    @Override
    public List<Moniteur> retrieveAllMoniteurs() {
        return (List<Moniteur>) moniteurRepo.findAll();
    }

    @Override
    public Moniteur addMoniteur(Moniteur moniteur) {
        return moniteurRepo.save(moniteur);
    }

    @Override
    public Moniteur updateMoniteur(Moniteur moniteur) {
        return moniteurRepo.save(moniteur) ;
    }

    @Override
    public Optional<Moniteur> retrieveMoniteur(Long numMoniteur) {

        // Handle the case where no Inscription was found with the specified ID, e.g., return an empty Optional or throw an exception.
        // You can change this to your desired behavior.
        return moniteurRepo.findById(numMoniteur);
    }

    @Override
    public void removeMoniteur(Long numMoniteur) {
        if (moniteurRepo.existsById(numMoniteur)) {
            moniteurRepo.deleteById(numMoniteur);
        } else {
            throw new IllegalArgumentException("Moniteur with ID " + numMoniteur + " not found");

        }
    }

    @Override
    public Moniteur addInstructorAndAssignToCourse(Moniteur moniteur, Long numCourse) {


            Cours cours = coursRepository.findById(numCourse).get();


            if (moniteur.getCours() == null) {
                moniteur.setCours(new HashSet<>());
            }
            moniteur.getCours().add(cours);
            moniteur = moniteurRepo.save(moniteur);

            return moniteur;


    }
}
