package com.example.demo.service

import com.example.demo.model.Client
import com.example.demo.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

   /* fun list (pageable: Pageable, client: Client): Page<Client> {
        val matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher(("fullname"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        return clientRepository.findAll(Example.of(client, matcher), pageable)
    }*/

    fun list ():List<Client>{
        return clientRepository.findAll()
    }
    fun save(client: Client): Client {
        try{
            client.nui?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("NUI no debe ser vacio")

            client.fullname?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Nombre no debe ser vacio")

            client.address?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Dirección no debe ser vacio")

            return clientRepository.save(client)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    fun update(client: Client): Client{
        try {
            clientRepository.findById(client.id)
                ?: throw Exception("ID no existe")

            return clientRepository.save(client)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(client: Client): Client{
        try{
            val response = clientRepository.findById(client.id)
                ?: throw Exception("ID no existe")
            response.apply {
                fullname=client.fullname
            }
            return clientRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Client?{
        return clientRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = clientRepository.findById(id)
                ?: throw Exception("ID no existe")
            clientRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}