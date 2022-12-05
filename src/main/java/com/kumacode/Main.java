package com.kumacode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

// https://youtu.be/-mwpoE0x0JQ?t=3605
@SpringBootApplication
@RestController
@RequestMapping("/api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @GetMapping()
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age){

    }
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest newCustomerRequest){
        Customer customer = new Customer();
        customer.setName(newCustomerRequest.name());
        customer.setEmail(newCustomerRequest.email());
        customer.setAge(newCustomerRequest.age());
        customerRepository.save(customer);
    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PostMapping("{customerId}")
    public void updateCustomer(@RequestBody NewCustomerRequest newCustomerRequest, @PathVariable("customerId") Integer id){
        Customer customer = customerRepository.findById(id).get();
        customer.setName(newCustomerRequest.name());
        customer.setEmail(newCustomerRequest.email());
        customer.setAge(newCustomerRequest.age());
        customerRepository.save(customer);
    }

}
