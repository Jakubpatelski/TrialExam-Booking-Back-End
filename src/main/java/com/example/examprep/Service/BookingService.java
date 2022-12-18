package com.example.examprep.Service;

import com.example.examprep.Entity.Booking;
import com.example.examprep.Entity.Member;
import com.example.examprep.Repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;


    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(int id){
        return bookingRepository.findById(id);
    }


    public boolean checkCarIsAvailable(int id, LocalDate date) {
        List<Booking> bookings = bookingRepository.findAllByCar_Id(id);
        for (Booking booking : bookings) {
            if (booking.getDate().equals(date)) {

                throw new IllegalStateException(booking.getCar() + " is not available on day " + date);
            }
        }
        return true;
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public boolean checkTime(Booking event) {
        LocalDate today = LocalDate.now();

        if (event.getDate().isBefore(today)) {
            throw new IllegalStateException(event.getDate() + " is in the past");
        }
        return true;
    }

    @Transactional
    public void updateBooking(int id, Booking project) {
        Booking storedProject = bookingRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Booking with ID " + id + " does not exist."));
        if (project.getDate() != null) {
            storedProject.setDate(project.getDate());
        }
        if (project.getCar() != null) {
            storedProject.setCar(project.getCar());
        }
        if (project.getMember() != null) {
            storedProject.setMember(project.getMember());

        }
    }

    public Booking updateBooking2(int id, Booking booking){
        if (bookingRepository.findById(id).isPresent()){
            return bookingRepository.save(booking);
        }
        return null;
    }

    public void deleteBookingById(int id){
        bookingRepository.deleteById(id);
    }
}