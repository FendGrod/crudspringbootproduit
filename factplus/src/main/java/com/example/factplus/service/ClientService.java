package com.example.factplus.service;

import com.example.factplus.model.Client;
import com.example.factplus.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private  final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // creer un nouvau client

    public Client create(Client newclient){
        return clientRepository.save(newclient);
    }

// update client
    public Client update(Long id , Client updateclient) {

        Client existclient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable" + id));
        existclient.setNom(updateclient.getNom());
        existclient.setPrenom(updateclient.getPrenom());
        existclient.setAdresse(updateclient.getAdresse());
        existclient.setEmail(updateclient.getEmail());
        existclient.setTelephone(updateclient.getTelephone());

    return clientRepository.save(existclient);

    }

// liste des client
   public List<Client> getAll(){
       return   clientRepository.findAll();
   }

   //lire un client

   public Optional<Client> read(Long id){
        return clientRepository.findById(id);
   }

   // supprimer un client
   public void delete(Long id){
        clientRepository.deleteById(id);
   }




}
