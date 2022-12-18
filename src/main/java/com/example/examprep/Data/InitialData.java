package com.example.examprep.Data;

import com.example.examprep.Entity.Booking;
import com.example.examprep.Entity.Car;
import com.example.examprep.Entity.Member;
import com.example.examprep.Repository.BookingRepository;
import com.example.examprep.Repository.CarRepository;
import com.example.examprep.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitialData implements CommandLineRunner {

    CarRepository carRepository;
    BookingRepository bookingRepository;
    MemberRepository memberRepository;

    @Autowired
    public InitialData(CarRepository carRepository, BookingRepository bookingRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
        this.memberRepository = memberRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        Car car1 = new Car("BMW","X6", 100, 15);
        Car car2 = new Car("Mercedes","A-Class", 200, 15);
        Car car3 = new Car("Porsche","G11", 300, 15);
        Car car4 = new Car("BMW","X5", 400, 15);
        Member member1 = new Member("Mike","Jopo", "wojska", "poznan", 100, true, 5.25);



        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        memberRepository.save(member1);

        Booking booking = new Booking(LocalDate.of(2022,12,23), car1, member1);

        bookingRepository.save(booking);



    }
}
