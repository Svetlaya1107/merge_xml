package com.vpolosov.trainee.mergexml.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * История платежа.
 *
 * @author Andrey Telepnev
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Metrics {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID metricsId;

    @Column(name = "is_complete")
    private boolean isComplete;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    private History history;
}
