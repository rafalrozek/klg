package pl.rafalrozek.klg.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Instant dateFrom;

    @Column
    private Instant dateTo;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "rent_object_id")
    private RentObject rentObject;

}