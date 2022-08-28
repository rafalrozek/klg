package pl.rafalrozek.klg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class RentObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal area;

    @Column
    private String description;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Person person;

}
