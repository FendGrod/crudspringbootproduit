package com.example.factplus.service;

import com.example.factplus.model.Client;
import com.example.factplus.model.Commande;
import com.example.factplus.model.Produit;
import com.example.factplus.repository.ClientRepository;
import com.example.factplus.repository.CommandeRepository;
import com.example.factplus.repository.ProduitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ProduitRepository produitRepository;

    public CommandeService(
            CommandeRepository commandeRepository,
            ProduitRepository produitRepository ,
            ClientRepository clientRepository)
    {
this.clientRepository=clientRepository;
this.commandeRepository=commandeRepository;
this.produitRepository =produitRepository;
    }

    public Commande create(Commande newcommande){
        Client client = clientRepository.findById(newcommande.getClient().getId())
                .orElseThrow(()->new RuntimeException("client introuvble"));

        Produit produit = produitRepository.findById(newcommande.getProduit().getId())
                .orElseThrow(()->new RuntimeException("produit introuvable"));


        if (newcommande.getQtec() > produit.getQtestock() ){
            System.out.println("la quantite commande est inferieura la quantite en stock");
        }

            // Calculer le montant total
            Double montantTotal = newcommande.getQtec() * produit.getPu();
            newcommande.setMontant(montantTotal);

            // Diminuer le stock
            produit.setQtestock(produit.getQtestock() - newcommande.getQtec());
            produitRepository.save(produit);

            // Associer le client et le produit
            newcommande.setClient(client);
            newcommande.setProduit(produit);

            return commandeRepository.save(newcommande);



    }

    // MODIFIER une commande
    @Transactional
    public Commande update(Long id, Commande commandeModifiee) {
        Commande commandeExistante = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        int ancienneQuantite = commandeExistante.getQtec();
        int nouvelleQuantite = commandeModifiee.getQtec();
        int difference = nouvelleQuantite - ancienneQuantite;

        if (difference != 0) {
            Produit produit = commandeExistante.getProduit();
            int nouveauStock = produit.getQtestock() - difference;

            if (nouveauStock < 0) {
                throw new RuntimeException("Stock insuffisant pour modifier la commande");
            }

            produit.setQtestock(nouveauStock);
            produitRepository.save(produit);

            // Recalculer le montant total
            Double nouveauMontant = nouvelleQuantite * produit.getPu();
            commandeExistante.setMontant(nouveauMontant);
            commandeExistante.setQtec(nouvelleQuantite);
        }

        return commandeRepository.save(commandeExistante);
    }

    // LISTER les commandes d'un client
    public List<Commande> getByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        return commandeRepository.findByClientId(clientId);
    }

    // LISTER les commandes d'un produit
    public List<Commande> getByProduit(Long produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        return commandeRepository.findByProduitId(produitId);
    }










    public List<Commande> getAll(){
        return this.commandeRepository.findAll();
    }


    public void delete(Long id){
        this.commandeRepository.deleteById(id);
    }
}
