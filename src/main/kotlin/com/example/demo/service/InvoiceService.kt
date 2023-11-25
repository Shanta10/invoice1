package com.example.demo.service

import com.example.demo.model.Client
import com.example.demo.model.Invoice
import com.example.demo.repository.ClientRepository
import com.example.demo.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class InvoiceService {
    @Autowired
    lateinit var clientRepository: ClientRepository
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository
    fun list ():List<Invoice>{
        return invoiceRepository.findAll()
    }
    fun save(invoice: Invoice): Invoice {
        try {
            clientRepository.findById(invoice.clientId)
                ?: throw Exception("Id de la conferencia no encontrada")
            //El objeto debe estar verificado.
            invoice.code?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Código no debe ser vacio")
            invoice.create_at
                ?: throw Exception("Fecha no debe ser vacio")
            invoice.total?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Total no debe ser vacio")


            return invoiceRepository.save(invoice)

        }catch (ex : Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
    fun update(invoice: Invoice): Invoice {
        try {
            invoiceRepository.findById(invoice.id)
                ?: throw Exception("ID no existe")

            return invoiceRepository.save(invoice)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(invoice: Invoice): Invoice {
        try{
            val response = invoiceRepository.findById(invoice.id)
                ?: throw Exception("ID no existe")
            response.apply {
                code=invoice.code
            }
            return invoiceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): Invoice?{
        return invoiceRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = invoiceRepository.findById(id)
                ?: throw Exception("ID no existe")
            invoiceRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
