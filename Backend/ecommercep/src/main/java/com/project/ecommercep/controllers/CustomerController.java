package com.project.ecommercep.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ecommercep.entities.Customer;
import com.project.ecommercep.services.CustomerService;

//CORS izinleri tanımladım (Securityi kaldırdım spring başlarken bunu da yazmam gerekli). CartController sınıfı.
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

    //Dependenciyleri enjekte ettiğim constructor metodum
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Yeni bir müşteri ekleyen endpoint.
    @PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    //Belirli bir müşteriyi ID'ye göre getiren endpoint.
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Tüm müşterileri getiren endpoint.
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }
}
