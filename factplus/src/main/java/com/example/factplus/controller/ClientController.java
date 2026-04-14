package com.example.factplus.controller;

import com.example.factplus.model.Client;
import com.example.factplus.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
         this.clientService = clientService;
    }

   @PostMapping
    public Client create(@RequestBody Client newclient){
        return clientService.create(newclient);
   }

   @PutMapping("/{id}")
    public Client update(@PathVariable Long id ,@RequestBody Client updateclient){
        return clientService.update(id, updateclient);
   }

   @GetMapping
    public List<Client> getAll( ){
        return  clientService.getAll();
   }

   @GetMapping("/{id}")
   public Optional<Client> read(@PathVariable Long id){
        return  clientService.read(id);
   }

   @DeleteMapping("/{id}")
    public void delete(@PathVariable  Long id){
        clientService.delete(id);
   }
}
