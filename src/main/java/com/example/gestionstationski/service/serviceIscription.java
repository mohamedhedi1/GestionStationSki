package com.example.gestionstationski.service;


import com.example.gestionstationski.configs.UtilityFunctions;
import com.example.gestionstationski.entities.Cours;
import com.example.gestionstationski.entities.Inscription;
import com.example.gestionstationski.entities.Skieur;
import com.example.gestionstationski.enums.Support;
import com.example.gestionstationski.enums.TypeCours;
import com.example.gestionstationski.repository.CoursRepository;
import com.example.gestionstationski.repository.InscriptionRepository;
import com.example.gestionstationski.repository.SkieurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class serviceIscription implements IserviceInscription{

    @Autowired
    InscriptionRepository inscriptionRepo;
    @Autowired
    SkieurRepository skieurRepository;
    @Autowired
    CoursRepository coursRepository;
    @Override
    public List<Inscription> retrieveAllInscriptions() {
        return (List<Inscription>) inscriptionRepo.findAll();
    }

    @Override
    public Inscription addInscriptions(Inscription inscription) {
        return inscriptionRepo.save(inscription);
    }

    @Override
    public Inscription updateInscriptions(Inscription inscription) {
        return inscriptionRepo.save(inscription);
    }


    @Override
    public Optional<Inscription> retrieveInscriptions(Long numInscription) {
        Optional<Inscription> inscriptionOptional = inscriptionRepo.findById(numInscription);

        if (inscriptionOptional.isPresent()) {
            return inscriptionOptional;
        } else {

            throw new IllegalArgumentException("Cours with ID " + numInscription + " not found");
        }
    }

    @Override
    public void removeInscriptions(Long numInscription) {

        inscriptionRepo.deleteById(numInscription);
        inscriptionRepo.existsById(numInscription);

    }

    @Override
    public Inscription addRegistrationAndAssignToSkieur(Inscription inscription, Long numSkieur) {

        Skieur skieur = skieurRepository.findById(numSkieur).get();
        inscription.setSkieurs(skieur);
        return inscriptionRepo.save(inscription);
    }
@Transactional
    @Override
    public Inscription assignRegistrationToCourse(Long numInscription, Long numCours) {
        Cours cours = coursRepository.findById(numCours).orElse(null);
        Inscription inscription = inscriptionRepo.findById(numInscription).orElse(null);

        inscription.setCours(cours);
        inscriptionRepo.save(inscription);

     return inscription;
    }

    @Override
    public Inscription addRegistrationAndAssignToSkieurAndCours(Inscription inscription, Long numCours, Long numSkieur) {

       Cours cours =  coursRepository.getCoursByNumCours(numCours);
       Skieur skieur=  skieurRepository.getSkieurByNumSkieur(numSkieur);

        int age = UtilityFunctions.calculateAge(skieur.getDateNaissance());
        if(cours.getTypeCours().equals(TypeCours.COLLECTIF_ADULTE) && age > 18){
            if (cours.getInscriptions().size() < 1){

                inscription.setSkieurs(skieur);
                inscription.setCours(cours);
                inscriptionRepo.save(inscription);


            }
        } else if (cours.getTypeCours().equals(TypeCours.COLLECTIF_ENFANT) && age < 18) {
            if(cours.getInscriptions().size() < 1){

                inscription.setSkieurs(skieur);
                inscription.setCours(cours);
                inscriptionRepo.save(inscription);
            }

        }else {
            log.info("error");
        }


        return inscription;
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numMoniteur, Support support) {
        return inscriptionRepo.numWeeksCoursOfMoniteurBySupport(numMoniteur,support);
    }
}
