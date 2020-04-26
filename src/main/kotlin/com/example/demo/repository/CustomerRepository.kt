package com.example.demo.repository

import com.example.demo.entity.Customer
import com.example.demo.util.info
import com.example.demo.util.warn
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(private val sessionFactory: SessionFactory) {

    private fun Session.getCustomer(id: Int): Customer? = get(Customer::class.java, id)

    fun saveCustomer(customer: Customer): Int = with(sessionFactory.currentSession) {
        val id = save(customer).toString().toInt()
        info("Customer $customer saved successfully with id $id")
        id
    }

    fun getAllCustomers(): MutableList<Customer> = with(sessionFactory.currentSession) {
        createQuery("SELECT a FROM customer a", Customer::class.java).resultList
    }

    fun updateCustomer(customer: Customer) = with(sessionFactory.currentSession) {
        update(customer)
        info("Customer $customer updated successfully")
    }

    fun getById(id: Int): Customer? = with(sessionFactory.currentSession) {
        val customer = getCustomer(id)
        if (customer == null) warn("Customer not found with id $id")
        else info("Customer $customer found")
        customer
    }

    fun delete(id: Int) = with(sessionFactory.currentSession) {
        val customer = getCustomer(id)
        if (customer == null) warn("Customer not found with id $id")
        else {
            delete(customer)
            info("Deleted customer $customer")
        }
    }
}