package com.example.factplus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Integer qtestock;
    private Double pu;

    @OneToMany(mappedBy = "produit")
    @JsonIgnore  // "client" = nom de l'attribut dans Commande
    private List<Commande> commandes;  // ← List, pas un objet seul
}
