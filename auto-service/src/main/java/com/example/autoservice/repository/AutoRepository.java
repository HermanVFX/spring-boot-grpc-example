package com.example.autoservice.repository;

import com.example.autoservice.entity.Auto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface AutoRepository extends CrudRepository<Auto, Long> {

    @Query(
            value = "SELECT a.id, a.vin, a.state_number as stateNumber, a.driver_id as driverId, a.make, a.age, d.detail_code as DetailCode FROM auto u" +
                    "   join detain d on d.id = a.detail_id" +
                    "   where a.is_active = true and d.is_active = true " +
                    "  order by u.created_at desc",
            nativeQuery = true
    )
    Page<Auto> findAllBySort(Pageable pageable);
}
