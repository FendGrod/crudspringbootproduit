package com.example.factplus.controller;

import com.example.factplus.model.Commande;
import com.example.factplus.service.CommandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    // CREATE - POST /api/commandes
    @PostMapping
    public Commande create(@RequestBody Commande commande) {
        return commandeService.create(commande);
    }

    // UPDATE - PUT /api/commandes/{id}
    @PutMapping("/{id}")
    public Commande update(@PathVariable Long id, @RequestBody Commande commande) {
        return commandeService.update(id, commande);
    }

    // GET BY CLIENT - GET /api/commandes/client/{clientId}
    @GetMapping("/client/{clientId}")
    public List<Commande> getByClient(@PathVariable Long clientId) {
        return commandeService.getByClient(clientId);
    }

    // GET BY PRODUIT - GET /api/commandes/produit/{produitId}
    @GetMapping("/produit/{produitId}")
    public List<Commande> getByProduit(@PathVariable Long produitId) {
        return commandeService.getByProduit(produitId);
    }
}
