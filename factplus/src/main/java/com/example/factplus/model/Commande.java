package com.example.factplus.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Integer qtec;
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    Produit produit;
}
