package com.example.demo.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "invoice_view")
class InvoiceView {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var code: String? = null
    var create_at: Date? = null
    var total: String? = null
    @Column (name="client_id")
    var clientId: Long? = null
    @Column (name="client_fullname")
    var clienteFullname:String? = null
}