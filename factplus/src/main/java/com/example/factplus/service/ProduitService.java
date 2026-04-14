package com.example.factplus.service;

import com.example.factplus.model.Produit;
import com.example.factplus.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository){
        this.produitRepository = produitRepository;
    }

    public Produit create(Produit newproduit){
        return  produitRepository.save(newproduit);
    }

    public Produit update(Long id , Produit updateproduit){
        Produit exitproduit = produitRepository.findById(id)
                .orElseThrow(()->new RuntimeException("produit introuvable" + id));

        exitproduit.setNom(exitproduit.getNom());
        exitproduit.setDescription(exitproduit.getDescription());
        exitproduit.setQtestock(exitproduit.getQtestock());
        exitproduit.setPu(exitproduit.getPu());

        return produitRepository.save(exitproduit);
    }


    public List<Produit> getAll(){
        return produitRepository.findAll();
    }


    public Optional<Produit> read(Long id){
        return produitRepository.findById(id);
    }

    public void delete(Long id){
        produitRepository.deleteById(id);
    }

}


