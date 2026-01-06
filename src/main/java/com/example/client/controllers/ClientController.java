package com.example.client.controllers;

import com.example.client.dto.ClientDTO;
import com.example.client.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClientDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO insert(@RequestBody ClientDTO dto) {
        return service.insert(dto);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Long id, @RequestBody ClientDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
