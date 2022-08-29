package pl.rafalrozek.klg.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.rafalrozek.klg.dto.ReservationAddDto;
import pl.rafalrozek.klg.dto.ReservationDto;
import pl.rafalrozek.klg.dto.ReservationEditDto;
import pl.rafalrozek.klg.model.Person;
import pl.rafalrozek.klg.model.RentObject;
import pl.rafalrozek.klg.model.Reservation;
import pl.rafalrozek.klg.repository.PersonRepository;
import pl.rafalrozek.klg.repository.RentObjectRepository;
import pl.rafalrozek.klg.repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RentObjectRepository rentObjectRepository;
    private final PersonRepository personRepository;

    public Iterable<Reservation> get() {
        return reservationRepository.findAll();
    }

    public Reservation add(ReservationAddDto reservation) throws Exception {
        Person person = personRepository.findById(reservation.getPersonId())
                .orElseThrow(() -> new Exception("Person ID not found"));

        RentObject rentObject = rentObjectRepository.findById(reservation.getRentObjectId())
                .orElseThrow(() -> new Exception("RentObject ID not found"));

        if(isObjectFree(reservation)){
            throw new Exception("RentObject is not free");
        }

        rentObject.setPerson(person);

        Reservation newReservation = Reservation.builder()
                .person(person)
                .rentObject(rentObject)
                .dateFrom(reservation.getDateFrom())
                .dateTo(reservation.getDateTo())
                .description(reservation.getDescription())
                .price(reservation.getPrice())
                .build();

        return reservationRepository.save(newReservation);
    }

    private Boolean isObjectFree(ReservationAddDto reservation) {
        return reservationRepository.existsByDateFromBetweenAndDateToBetweenAndRentObjectId(reservation.getDateFrom(), reservation.getDateTo(), reservation.getDateFrom(), reservation.getDateTo(), reservation.getRentObjectId());
    }

    public Reservation edit(ReservationEditDto reservation) throws Exception {
        Reservation newReservation = reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new Exception("Person ID not found"));

        Person person = personRepository.findById(reservation.getPersonId())
                .orElseThrow(() -> new Exception("Person ID not found"));

        RentObject rentObject = rentObjectRepository.findById(reservation.getRentObjectId())
                .orElseThrow(() -> new Exception("RentObject ID not found"));

        newReservation.setPerson(person);
        newReservation.setRentObject(rentObject);
        newReservation.setDateFrom(reservation.getDateFrom());
        newReservation.setDateTo(reservation.getDateTo());
        newReservation.setDescription(reservation.getDescription());
        newReservation.setPrice(reservation.getPrice());

        return reservationRepository.save(newReservation);
    }

    public List<ReservationDto> getByPerson(Long id) throws Exception {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new Exception("Person ID not found"));

        return person.getReservations().stream().map(i -> ReservationDto.builder()
                .dateFrom(i.getDateFrom())
                .dateTo(i.getDateTo())
                .description(i.getDescription())
                .price(i.getPrice())
                .build())
                .collect(Collectors.toList());

    }

    public List<ReservationDto> getByObject(Long id) throws Exception {
        RentObject rentObject = rentObjectRepository.findById(id)
                .orElseThrow(() -> new Exception("RentObject ID not found"));

        return reservationRepository.findAllByRentObject(rentObject).stream().map(i -> ReservationDto.builder()
                        .dateFrom(i.getDateFrom())
                        .dateTo(i.getDateTo())
                        .description(i.getDescription())
                        .price(i.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
