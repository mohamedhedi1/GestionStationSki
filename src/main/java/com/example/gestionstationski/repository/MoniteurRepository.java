package com.example.gestionstationski.repository;


import com.example.gestionstationski.entities.Moniteur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoniteurRepository extends CrudRepository<Moniteur,Long> {



}
