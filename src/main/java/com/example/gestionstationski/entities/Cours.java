package com.example.gestionstationski.entities;

import com.example.gestionstationski.enums.Support;
import com.example.gestionstationski.enums.TypeCours;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long numCours ;
    Integer niveau;

    @Enumerated(EnumType.STRING)
    TypeCours TypeCours;


    @Enumerated(EnumType.STRING)
    Support support;

    Float prix;
    Integer creneau;

    @OneToMany(mappedBy = "cours")
    Set<Inscription> inscriptions ;





}
