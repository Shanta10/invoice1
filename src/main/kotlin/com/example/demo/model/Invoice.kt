package com.example.demo.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "invoice")
class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var code: String? = null
    var create_at: Date? = null
    var total: String? = null
    @Column (name="client_id")
    var clientId: Long? = null
}