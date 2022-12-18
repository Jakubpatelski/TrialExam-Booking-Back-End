package com.example.examprep.Repository;

import com.example.examprep.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository <Booking, Integer> {

//    @Query("from Booking where car.id = ?1")
    List<Booking> findAllByCar_Id(int id);




}
