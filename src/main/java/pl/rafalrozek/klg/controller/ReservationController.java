package pl.rafalrozek.klg.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rafalrozek.klg.dto.ReservationAddDto;
import pl.rafalrozek.klg.dto.ReservationEditDto;
import pl.rafalrozek.klg.service.ReservationService;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<?> addReservation(@RequestBody ReservationAddDto reservation){
        try{
            reservationService.add(reservation);
            return ResponseEntity.ok("Reservation added.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> editReservation(@RequestBody ReservationEditDto reservation){
        try{
            reservationService.edit(reservation);
            return ResponseEntity.ok("Reservation edited.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<?> getByPerson(@PathVariable Long id){
        try {
            return ResponseEntity.ok(reservationService.getByPerson(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/object/{id}")
    public ResponseEntity<?> getByObject(@PathVariable Long id){
        try {
            return ResponseEntity.ok(reservationService.getByObject(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
