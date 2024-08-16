package com.vpolosov.trainee.mergexml.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Информация о валидируемых файлах и результатах их валидации.
 *
 * @author Andrey Telepnev
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ValidationFileHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID validationId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "doc_ref")
    private String docRef;

    @Column(name = "is_success")
    private boolean isSuccess;

    @Column(name = "fail_fields")
    private String failFields;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;

    @ManyToOne
    @Column(name = "validation_process_id")
    private boolean isComplete;
}
