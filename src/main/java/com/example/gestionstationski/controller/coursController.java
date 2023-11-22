package com.example.gestionstationski.controller;


import com.example.gestionstationski.entities.Cours;
import com.example.gestionstationski.service.IserviceCours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cours")
public class coursController {

@Autowired
    IserviceCours iserviceCours;

@GetMapping
    public List<Cours> getAll(){
    return iserviceCours.retrieveAllCourses();
}

@GetMapping("/{numCours}")
public Optional<Cours> retrieveCours(@PathVariable("numCours") Long numCours){

    return  iserviceCours.retrieveCours(numCours);
}

@PostMapping("/addCours")
    public Cours addCours(@RequestBody Cours cours){
    return  iserviceCours.addCours(cours);
}

@PutMapping("/updateCours")
    public Cours updateCours(@RequestBody Cours cours){
    return iserviceCours.updateCours(cours);
}

@DeleteMapping("/deleteCours/{numCours}")
    public void removeCours(@PathVariable("numCours") Long numCours){
    iserviceCours.removeCours(numCours);
}

}
