@file:Suppress("unused")

package com.example.demo.entity

import javax.persistence.*

@Entity(name = "customer")
data class Customer(
        @Column(name = "first_name") var firstName: String = "",
        @Column(name = "last_name") var lastName: String = "",
        var email: String = ""
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "unused")
    lateinit var id: Integer
}