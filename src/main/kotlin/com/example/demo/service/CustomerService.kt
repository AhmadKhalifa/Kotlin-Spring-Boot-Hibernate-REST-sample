package com.example.demo.service

import com.example.demo.entity.Customer
import com.example.demo.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    @Transactional
    fun saveCustomer(customer: Customer) = customerRepository.saveCustomer(customer)

    @Transactional
    fun updateCustomer(customer: Customer) = customerRepository.updateCustomer(customer)

    @Transactional
    fun getAllCustomers() = customerRepository.getAllCustomers()

    @Transactional
    fun getCustomerById(customerId: Int) = customerRepository.getById(customerId)

    @Transactional
    fun deleteCustomer(customerId: Int) = customerRepository.delete(customerId)
}