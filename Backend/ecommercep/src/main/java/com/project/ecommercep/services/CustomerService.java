package com.project.ecommercep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommercep.entities.Customer;
import com.project.ecommercep.repos.CustomerRepository;

//Servis sınıfı anatosyonu olarak işaretledim.
@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

    //Dependenciyleri(bağımlılıklar) enjekte ettiğim constructor metodum
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Yeni bir müşteri ekleyen metodum.
    public Customer addCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    //Belirli bir müşteriyi ID'ye göre getiren metodum.
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Tüm müşterileri getiren metodum.
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
