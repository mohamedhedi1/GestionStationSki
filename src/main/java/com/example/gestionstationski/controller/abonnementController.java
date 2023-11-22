package com.example.gestionstationski.controller;


import com.example.gestionstationski.entities.Abonnement;
import com.example.gestionstationski.enums.TypeAbonnement;
import com.example.gestionstationski.service.IserviceAbonnement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/abonnement")
@RequiredArgsConstructor
public class abonnementController {

  private  final  IserviceAbonnement iserviceAbonnement;

    @GetMapping
    public List<Abonnement> getAll(){
        return iserviceAbonnement.retrieveAllAbonnements();
    }
    @PostMapping("/addAbon")
    public Abonnement addAbonnement(@RequestBody Abonnement abonnement){
        return iserviceAbonnement.addAbonnements(abonnement);
    }
    @GetMapping("/{numAbonnement}")
    public Abonnement getByIdAbonnement(@PathVariable("numAbonnement") Long numAbon){
        return  iserviceAbonnement.retrieveAbonnements(numAbon);
    }

    @PutMapping("/updateAbonnement")
    public Abonnement updateAbonnement (@RequestBody  Abonnement abonnement){
        return iserviceAbonnement.updateAbonnements(abonnement);
    }

    @DeleteMapping("/deleteAbonnement/{numAbon}")
    public void deleteAbonnement(@PathVariable("numAbon") Long numAbon){

        iserviceAbonnement.removeAbonnements(numAbon);

    }

    @GetMapping("/subscriptions")
    public List<Abonnement> getSubscriptionsByType(@RequestParam TypeAbonnement typeAbonnement) {
        return iserviceAbonnement.getAbonnementByType(typeAbonnement);
    }

    @GetMapping("/getAbonnementByDate")
    public List<Abonnement> getAbonnementByDate(@RequestParam LocalDate dateDebut , @RequestParam LocalDate dateFin){
return iserviceAbonnement.findByDateDebutAfterAndDataFinAfter(dateDebut,dateFin);

    }

}
