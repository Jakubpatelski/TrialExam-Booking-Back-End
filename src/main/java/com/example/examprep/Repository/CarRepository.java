package com.example.examprep.Repository;

import com.example.examprep.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CarRepository extends JpaRepository <Car, Integer> {
}
