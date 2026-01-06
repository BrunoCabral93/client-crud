package com.example.client.services;

import com.example.client.dto.ClientDTO;
import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ClientDTO::new)
                .toList();
    }

    public ClientDTO findById(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return new ClientDTO(entity);
    }

    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);

        return new ClientDTO(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
