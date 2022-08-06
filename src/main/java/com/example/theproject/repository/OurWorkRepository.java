package com.example.theproject.repository;

import com.example.theproject.model.entity.OurWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OurWorkRepository extends JpaRepository<OurWork, Long> {

}
