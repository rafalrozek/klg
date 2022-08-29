package pl.rafalrozek.klg.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class ReservationAddDto {

    private Instant dateFrom;
    private Instant dateTo;

    private String description;
    private BigDecimal price;

    private Long personId;
    private Long rentObjectId;

}
