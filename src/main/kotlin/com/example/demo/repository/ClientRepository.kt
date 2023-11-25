package com.example.demo.repository

import com.example.demo.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long?> {

    fun findById (id: Long?): Client?
}