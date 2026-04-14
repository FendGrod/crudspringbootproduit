package com.example.factplus.repository;

import com.example.factplus.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    List<Commande> findByClientId(Long clientId);
    List<Commande> findByProduitId(Long produitId);
}
