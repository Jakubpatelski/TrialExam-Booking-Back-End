package com.example.examprep.Controller;

import com.example.examprep.Entity.Booking;
import com.example.examprep.Entity.Member;
import com.example.examprep.Repository.BookingRepository;
import com.example.examprep.Service.BookingService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;
    private BookingService bookingService;


    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAll(){
        return bookingService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findBookingById(@PathVariable int id){
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()){
            return new ResponseEntity<Booking>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/car/{id}")
    public List<Booking> getAllBookingsByCarID(@PathVariable("id") int id){
        return bookingRepository.findAllByCar_Id(id);
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking event) {
        if (bookingService.checkCarIsAvailable(event.getCar().getId(), event.getDate()) && bookingService.checkTime(event)){
            return new ResponseEntity<> (bookingService.addBooking(event), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public void editBooking(@PathVariable int id, @RequestBody Booking booking){

            bookingService.updateBooking(id, booking);



    }

    @PutMapping("/{id}")
    public Booking editBookingPut(@PathVariable int id, @RequestBody Booking booking){

            return bookingService.updateBooking2(id, booking);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable int id) {
        try {
            bookingService.deleteBookingById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }








}
