package com.vpolosov.trainee.mergexml.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * История платежа.
 *
 * @author Ali Takushinov
 * @author Maksim Litvinenko
 * @author Andrey Telepnev
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProcess {

    /**
     * Ref Документа.
     */
    @Id
    @Column(name = "doc_ref")
    private String docRef;

    /**
     * Дата совершения платежа.
     */
    @Column(name = "document_date")
    private LocalDate documentDate;

    /**
     * Дата и время загрузки.
     */
    @Column(name = "date_time_upload")
    private LocalDateTime dateTimeUpload;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL)
    private List<ValidationFileHistory> metrics;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy h
            ? h.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy h
            ? h.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        ValidationProcess validationProcess = (ValidationProcess) o;
        return getDocRef() != null && Objects.equals(getDocRef(), validationProcess.getDocRef());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy h
            ? h.getHibernateLazyInitializer().getPersistentClass().hashCode()
            : getClass().hashCode();
    }
}
