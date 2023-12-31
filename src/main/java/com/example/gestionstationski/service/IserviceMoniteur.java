package com.example.gestionstationski.service;



import com.example.gestionstationski.entities.Moniteur;

import java.util.List;
import java.util.Optional;

public interface IserviceMoniteur {
    List<Moniteur> retrieveAllMoniteurs();

    Moniteur addMoniteur(Moniteur moniteur);

    Moniteur updateMoniteur(Moniteur moniteur);

    Optional<Moniteur> retrieveMoniteur(Long numMoniteur);

    void removeMoniteur(Long numMoniteur);

    Moniteur addInstructorAndAssignToCourse(Moniteur moniteur, Long numCourse);

}
