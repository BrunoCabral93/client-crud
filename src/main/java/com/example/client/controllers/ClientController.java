package com.example.client.controllers;

import com.example.client.dto.ClientDTO;
import com.example.client.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ClientDTO> findAll(Pageable pageable) {
        return service.findAllPaged(pageable);
    }


    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO insert(@Valid @RequestBody ClientDTO dto) {
        return service.insert(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ClientDTO dto) {

        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
