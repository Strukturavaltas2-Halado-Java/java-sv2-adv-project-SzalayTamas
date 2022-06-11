package com.example.javasv2advprojectszalaytamas.repositori;

import com.example.javasv2advprojectszalaytamas.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findAllByCustomerId(Long id);
}
