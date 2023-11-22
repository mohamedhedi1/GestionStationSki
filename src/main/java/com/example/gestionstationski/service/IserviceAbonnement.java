package com.example.gestionstationski.service;


import com.example.gestionstationski.entities.Abonnement;
import com.example.gestionstationski.enums.TypeAbonnement;

import java.time.LocalDate;
import java.util.List;

public interface IserviceAbonnement {
    List<Abonnement> retrieveAllAbonnements();

    Abonnement addAbonnements(Abonnement abonnement);

    Abonnement updateAbonnements(Abonnement abonnement);

    Abonnement retrieveAbonnements(Long numAbon);

    void removeAbonnements(Long numAbon);

    List<Abonnement> getSubsciptionByType(TypeAbonnement typeAbonnement);

    List<Abonnement> getAbonnementByType(TypeAbonnement typeAbonnement);

    List<Abonnement> getAbonnementByDate(LocalDate dateDebut , LocalDate dateFin);

    List<Abonnement> findByDateDebutAfterAndDataFinAfter(LocalDate dateDebut , LocalDate dateFin);
}
