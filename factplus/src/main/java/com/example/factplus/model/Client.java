package com.example.factplus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;


@Entity
@Data
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private Number telephone;
    private  String email;

    @OneToMany(mappedBy = "client")
    @JsonIgnore  // "client" = nom de l'attribut dans Commande
    private List<Commande> commandes;  // ← List, pas un objet seul
}

