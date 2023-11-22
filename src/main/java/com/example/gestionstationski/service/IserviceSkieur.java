package com.example.gestionstationski.service;


import com.example.gestionstationski.entities.Skieur;
import com.example.gestionstationski.enums.TypeAbonnement;

import java.util.List;
import java.util.Optional;

public interface IserviceSkieur {

    List<Skieur> retrieveAllSkieurs();

    Skieur addSkieur(Skieur skieur);

    Skieur updateSkieur(Skieur skieur);

    Optional<Skieur> retrieveSkieur(Long numSkieur);
    void removeSkieur(Long numSkieur );

    long assignSkieurToPiste(Long numSkieur , Long numPiste);

    Skieur addSkieurAndAssignToCourse(Skieur skieur , Long numCourse);

    List<Skieur> retrieveSkieurBySubscriptionType(TypeAbonnement typeAbonnement);


}
