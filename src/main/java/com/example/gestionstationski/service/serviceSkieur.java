package com.example.gestionstationski.service;


import com.example.gestionstationski.entities.Cours;
import com.example.gestionstationski.entities.Inscription;
import com.example.gestionstationski.entities.Piste;
import com.example.gestionstationski.entities.Skieur;
import com.example.gestionstationski.enums.TypeAbonnement;
import com.example.gestionstationski.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class serviceSkieur implements  IserviceSkieur{

    final SkieurRepository skieurRep;
    final  PisteRepository pisteRepository;
    final  AbonnementRepository abonnementRepository;
    final InscriptionRepository inscriptionRepository;
    final CoursRepository coursRepository;

    @Override
    public List<Skieur> retrieveAllSkieurs() {
        return (List<Skieur>) skieurRep.findAll();
    }

    @Override
    public Skieur addSkieur(Skieur skieur) {
        return skieurRep.save(skieur);
    }

    @Override
    public Skieur updateSkieur(Skieur skieur) {
          return skieurRep.save(skieur);
    }


    @Override
    public Optional<Skieur> retrieveSkieur(Long numSkieur) {

        Optional<Skieur> skieur = skieurRep.findById(numSkieur);
        if(skieur.isPresent()){
            return skieur;
        }else{
            throw new IllegalArgumentException("Skieur ID"+numSkieur+" is not defined");
        }

    }

    @Override
    public void removeSkieur(Long numSkieur) {
         if(skieurRep.existsById(numSkieur)){
             skieurRep.deleteById(numSkieur);
         }else {
             throw new IllegalArgumentException("Skieur ID"+numSkieur+" is not defined");
         }
    }

    @Override
    public long assignSkieurToPiste(Long numSkieur, Long numPiste) {

        Skieur skieur = skieurRep.findById(numSkieur).orElse(null);
        System.out.println(skieur);
        Piste piste = pisteRepository.findById(numPiste).orElse(null);
        System.out.println(piste);
        skieur.getPistes().add(piste);
        skieurRep.save(skieur);

       return skieur.getNumSkieur();

    }
//@Transactional on l utilise lorsque une recuperation de la base donnee managed entity
    @Override
    public Skieur addSkieurAndAssignToCourse(Skieur skieur, Long numCourse) {
      //  Abonnement abonnement = skieur.getAbonnement(); car on a une aggregation entre skieur et abonnement

        //recuperation des objets
        Cours cours = coursRepository.findById(numCourse).orElse(null);

        Inscription inscription = skieur.getInscriptions().stream().findFirst().get();



        inscription.setSkieurs(skieur);
        inscription.setCours(cours);


        skieurRep.save(skieur);
        inscriptionRepository.save(inscription);

        return skieur;
    }

    @Override
    public List<Skieur> retrieveSkieurBySubscriptionType(TypeAbonnement typeAbonnement) {
        return skieurRep.findByAbonnementTypeAbonnement(typeAbonnement);

    }

}
