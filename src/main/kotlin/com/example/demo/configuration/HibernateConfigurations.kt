package com.example.demo.configuration

import com.example.demo.DemoApplication
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class HibernateConfigurations {

    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driverClassName: String

    @Value("\${spring.datasource.url}")
    private lateinit var jdbcUrl: String

    @Value("\${spring.datasource.username}")
    private lateinit var username: String

    @Value("\${spring.datasource.password}")
    private lateinit var password: String

    @Value("\${spring.jpa.database-platform}")
    private lateinit var dialect: String

    @Value("\${spring.jpa.show-sql}")
    private lateinit var _showSql: String

    private val showSql: Boolean
    get() = "true".equals(_showSql, ignoreCase = true)

    @Bean
    fun sessionFactory() = LocalSessionFactoryBean().apply {
        setDataSource(dataSource())
        setPackagesToScan(DemoApplication::class.java.`package`.name)
        hibernateProperties = hibernateProperties()
    }

    @Bean
    fun dataSource(): DataSource = DriverManagerDataSource().apply {
        setDriverClassName(this@HibernateConfigurations.driverClassName)
        url = this@HibernateConfigurations.jdbcUrl
        username = this@HibernateConfigurations.username
        password = this@HibernateConfigurations.password
    }

    @Bean
    fun getTransactionManager() = HibernateTransactionManager().apply {
        sessionFactory = sessionFactory().`object`
    }

    private fun hibernateProperties() = Properties().apply {
        put("hibernate.dialect", this@HibernateConfigurations.dialect)
        put("hibernate.show_sql", this@HibernateConfigurations.showSql)
    }
}