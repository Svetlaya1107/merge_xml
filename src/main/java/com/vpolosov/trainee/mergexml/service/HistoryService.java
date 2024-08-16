package com.vpolosov.trainee.mergexml.service;

import com.vpolosov.trainee.mergexml.aspect.Loggable;
import com.vpolosov.trainee.mergexml.model.ValidationProcess;
import com.vpolosov.trainee.mergexml.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.vpolosov.trainee.mergexml.utils.Constant.FIRST_ELEMENT;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.DOCREF;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.DOCUMENT;
import static com.vpolosov.trainee.mergexml.utils.XmlTags.DOCUMENTDATE;

/**
 * Сервис хранения истории объединённых платежей.
 *
 * @author Ali Takushinov
 * @author Maksim Litvinenko
 */
@Service
@RequiredArgsConstructor
public class HistoryService {

    /**
     * Репозиторий для взаимодействия с {@link ValidationProcess}.
     */
    private final HistoryRepository historyRepository;

    /**
     * Формат даты.
     */
    private final DateTimeFormatter localDateFormat;

    /**
     * Часы для корректировки времени.
     */
    private final Clock clock;

    /**
     * Вернуть историю объединённых платежей.
     *
     * @param spec условия поиска всех записей.
     * @return все истории объединённых платежей по заданному условию.
     */
    @Loggable
    @Transactional(readOnly = true)
    public List<ValidationProcess> getHistoryListBySpec(Specification<ValidationProcess> spec) {
        return historyRepository.findAll(spec);
    }

    /**
     * Сохраняет историю платежа.
     *
     * @param validationProcess история платежа.
     */
    @Loggable
    @Transactional
    public void addHistory(ValidationProcess validationProcess) {
        historyRepository.save(validationProcess);
    }

    /**
     * Добавляет историю платежа по XML документу.
     *
     * @param document xml документ.
     */
    @Loggable
    @Transactional
    public void addHistoryFromTotal(Document document) {
        NodeList elementsByTagName = document.getElementsByTagName(DOCUMENT);
        for (int i = FIRST_ELEMENT; i < elementsByTagName.getLength(); i++) {
            String docRef = document.getElementsByTagName(DOCREF)
                .item(i)
                .getTextContent();
            String documentDate = document.getElementsByTagName(DOCUMENTDATE)
                .item(i)
                .getTextContent();
            var history = new ValidationProcess(
                docRef,
                LocalDate.parse(documentDate, localDateFormat),
                LocalDateTime.now(clock)
            );
            addHistory(history);
        }
    }
}
