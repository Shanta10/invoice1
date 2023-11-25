package com.example.demo.service

import com.example.demo.model.Product
import com.example.demo.model.Detail
import com.example.demo.repository.ProductRepository
import com.example.demo.repository.DetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var productRepository: ProductRepository
    @Autowired
    lateinit var detailRepository: DetailRepository
    fun list ():List<Detail>{
        return detailRepository.findAll()
    }
    fun save(detail: Detail): Detail {
        try {
            productRepository.findById(detail.productId)
                ?: throw Exception("Id de la conferencia no encontrada")
            //El objeto debe estar verificado.
            detail.quantity?.toString()?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Cantidad no debe ser vacio")
            detail.price?.toString()?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Precio no debe ser vacio")

            return detailRepository.save(detail)

        }catch (ex : Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
    fun update(detail: Detail): Detail {
        try {
            detailRepository.findById(detail.id)
                ?: throw Exception("ID no existe")

            return detailRepository.save(detail)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    /*fun updateName(detail: Detail): Detail {
        try{
            val response = detailRepository.findById(detail.id)
                ?: throw Exception("ID no existe")
            response.apply {
                nameassistant=assistant.nameassistant
            }
            return assistantRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }*/
    fun listById (id:Long?): Detail?{
        return detailRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = detailRepository.findById(id)
                ?: throw Exception("ID no existe")
            detailRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}