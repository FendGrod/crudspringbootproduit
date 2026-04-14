package com.example.factplus.controller;

import com.example.factplus.model.Produit;
import com.example.factplus.service.ProduitService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/produit")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService){
        this.produitService = produitService;
    }

    @GetMapping
    public List<Produit> getAll(){
        return produitService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Produit> read(@PathVariable Long id){
        return  produitService.read(id);
    }

    @PostMapping
    public Produit create(@RequestBody Produit newproduit){
        return produitService.create(newproduit);
    }

    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id , @RequestBody Produit updateprouit){
        return produitService.update(id,updateprouit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        produitService.delete(id);
    }



}
