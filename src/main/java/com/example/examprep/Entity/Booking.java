package com.example.examprep.Entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //LocalDateFormat: LocalDate.of(2022, 10, 10)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "car_id")
    Car car;

    @ManyToOne
    @JoinColumn(name = "member_id")

    Member member;

    public Booking(LocalDate date, Car car, Member member) {
        this.date = date;
        this.car = car;
        this.member = member;
    }

    public Booking() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
