package com.example.client.services;

import com.example.client.dto.ClientDTO;
import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import com.example.client.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(Pageable pageable) {
        Page<Client> page = repository.findAll(pageable);
        return page.map(ClientDTO::new);
    }

    public ClientDTO findById(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
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
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found");
        }
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
