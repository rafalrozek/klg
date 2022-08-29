package pl.rafalrozek.klg.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.rafalrozek.klg.dto.ReservationAddDto;
import pl.rafalrozek.klg.dto.ReservationEditDto;
import pl.rafalrozek.klg.model.Reservation;
import pl.rafalrozek.klg.service.ReservationService;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ReservationControllerTest {
    public static final ReservationAddDto EXAMPLE_ADD = ReservationAddDto.builder()
            .dateFrom(Instant.now())
            .dateTo(Instant.now().plus(30, ChronoUnit.DAYS))
            .description("Example")
            .personId(1L)
            .price(BigDecimal.valueOf(100))
            .rentObjectId(1L)
            .build();
    public static final ReservationEditDto EXAMPLE_EDIT = ReservationEditDto.builder()
            .dateFrom(Instant.now())
            .dateTo(Instant.now().plus(30, ChronoUnit.DAYS))
            .description("Example")
            .personId(1L)
            .price(BigDecimal.valueOf(100))
            .rentObjectId(1L)
            .id(1L)
            .build();
    ReservationService reservationService;

    @BeforeEach
    void setup() throws Exception {
        ReservationService reservationService = Mockito.mock(ReservationService.class);
        when(reservationService.get()).thenReturn(Collections.emptyList());
        when(reservationService.edit(EXAMPLE_EDIT)).thenReturn(new Reservation());
        when(reservationService.add(EXAMPLE_ADD)).thenReturn(new Reservation());
        this.reservationService = reservationService;
    }



    @Test
    void getReservation() {
        assertEquals(Collections.emptyList(), reservationService.get());

    }

    @Test
    void addReservation() throws Exception {

        try {
            assertNotNull(reservationService.add(EXAMPLE_ADD));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void editReservation() {

        try {
            assertNotNull(reservationService.edit(EXAMPLE_EDIT));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void getByObject() {
        try {
            assertEquals(Collections.emptyList(), reservationService.getByObject(1L));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void getByPerson() {
        try {
            assertEquals(Collections.emptyList(), reservationService.getByPerson(1L));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}