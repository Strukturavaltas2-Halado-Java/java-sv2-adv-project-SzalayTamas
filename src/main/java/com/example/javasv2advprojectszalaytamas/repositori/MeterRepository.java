package com.example.javasv2advprojectszalaytamas.repositori;

import com.example.javasv2advprojectszalaytamas.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
    List<Meter> findAllByCustomerId(Long id);
    @Query("select distinct c from Meter c left join c.measurements")
    List<Meter> findAllMeterWithMeasurements();
}
