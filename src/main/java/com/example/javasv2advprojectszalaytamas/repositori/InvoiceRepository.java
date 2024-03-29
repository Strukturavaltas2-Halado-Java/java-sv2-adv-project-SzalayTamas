package com.example.javasv2advprojectszalaytamas.repositori;

import com.example.javasv2advprojectszalaytamas.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findAllByCustomerId(Long id);
    Optional<Invoice> findByCustomerId(Long id);
}
