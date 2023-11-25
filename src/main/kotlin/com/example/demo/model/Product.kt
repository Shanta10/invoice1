package com.example.demo.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

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
    @NotNull(message="Campo obligatorio") //validate
    var price: Int? = null
    @NotNull(message="Campo obligatorio") //validate
    var stock: Int? = null
}
