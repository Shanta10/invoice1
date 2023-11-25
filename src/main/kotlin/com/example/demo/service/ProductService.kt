package com.example.demo.service

import com.example.demo.model.Product
import com.example.demo.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list ():List<Product>{
        return productRepository.findAll()
    }
    fun save(product: Product): Product {
        try {
            product.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Descripción no debe ser vacío")

            product.brand?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Brand no debe ser vacío")

            product.price?.toString()?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Precio no debe ser vacío")

            product.stock?.toString()?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Stock no debe ser vacío")

            return productRepository.save(product)

        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }

    fun update(product: Product): Product{
        try {
            productRepository.findById(product.id)
                ?: throw Exception("ID no existe")

            return productRepository.save(product)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(product: Product): Product{
        try{
            val response = productRepository.findById(product.id)
                ?: throw Exception("ID no existe")
            response.apply {
                description=product.description
            }
            return productRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Product?{
        return productRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = productRepository.findById(id)
                ?: throw Exception("ID no existe")
            productRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}