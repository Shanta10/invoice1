package com.example.demo.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "product")
class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @NotBlank(message="Campo obligatorio") //validate
    var description: String? = null
    @NotBlank(message="Campo obligatorio") //validate
    var brand: String? = null
    @NotBlank(message="Campo obligatorio") //validate
    var price: Int? = null
    @NotBlank(message="Campo obligatorio") //validate
    var stock: Int? = null
}
