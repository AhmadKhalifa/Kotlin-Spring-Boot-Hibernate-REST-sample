package com.example.demo.controller

import com.example.demo.entity.Customer
import com.example.demo.service.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    fun getAllCustomers() = customerService.getAllCustomers()

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable("id") id: Int) = customerService.getCustomerById(id)

    @PostMapping
    fun saveCustomer(@RequestBody customer: Customer) = customerService.saveCustomer(customer)

    @PutMapping
    fun updateCustomer(@RequestBody customer: Customer) = customerService.updateCustomer(customer)

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Int) = customerService.deleteCustomer(id)
}
