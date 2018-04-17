package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    public List<ClientDTO> findAllClients() {
        return clientRepository.findAll().stream().map(c-> clientMapper.map(c)).collect(toList());
        
        /*
         * 
         * Autre facon d #ecrire
         * List<Client> clients = clientRepository.findAll();
         * List<ClientDTO> dtos = new ArrayList<>();
         * for (Client client : clients){
         *  ClientDTO dto = clientMapper.map(client);
         *  dtos.add(dto);
         *  } 
         *  return dtos;
         * 
         * */
    }
    
    public ClientDTO findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::map)
                .orElseThrow(() ->
                        new IllegalArgumentException("? Client inconnu avec id = " + id +" ?")
                );
    }
}
