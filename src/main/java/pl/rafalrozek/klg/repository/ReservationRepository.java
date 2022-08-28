package pl.rafalrozek.klg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rafalrozek.klg.model.RentObject;
import pl.rafalrozek.klg.model.Reservation;

import java.time.Instant;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findAllByRentObject(RentObject rentObject);
    Boolean existsByDateFromBetweenAndDateToBetweenAndRentObjectId(Instant dateFrom, Instant dateFrom2, Instant dateTo, Instant dateTo2, Long rentObject_id);
}
