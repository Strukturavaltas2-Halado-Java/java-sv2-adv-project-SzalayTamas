package com.example.javasv2advprojectszalaytamas.repositori;

import com.example.javasv2advprojectszalaytamas.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select distinct c FROM Customer c left join fetch c.invoices ")
    List<Customer> findAllWithInvoice();


    Optional<Customer> findCustomerByContactContainingIgnoreCase(String email);
}
