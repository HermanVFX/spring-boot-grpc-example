package com.example.autoservice.repository;

import com.example.autoservice.entity.Auto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AutoRepository extends CrudRepository<Auto, UUID> {

}
