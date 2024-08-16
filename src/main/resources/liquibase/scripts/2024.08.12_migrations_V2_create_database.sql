1) Таблица validation_proccess
validation_proccess_id (uuid primary key для того, чтобы все валидируемые файлы были приписаны к одному процессу валидации)
dir_ref (varchar ссылка в windiws проводнике на каталог с валидируемыми файлами)
is_success (boolean прошел ли валидацию каталог)
total_doc_ref (varcahar ссылка на итоговый документ)
validation_proccess_date (timestamp дата завершения процесса ваклидации)
2) Таблица validation_file_history:
validation_id (uuid id primary key записи)
validation_proccess_id (uuid foreign key для того, чтобы все валидируемые файлы были приписаны к одному процессу валидации)
file_name (varchar имя файла)
doc_ref (varchar ссылка в windiws проводнике на валидируемый файл)
is_success (boolean прошел ли валидацию файл)
fail_fields (varchar поля, которые провалили валидацию)
validation_date (timestamp дата завершения валидации файла)

create table validation_process
(
    validation_process_id   UUID PRIMARY KEY,
    dir_ref                 VARCHAR(50) NOT NULL UNIQUE,
    is_success             BOOLEAN NOT NULL,
    total_doc_ref           VARCHAR(50),
    validation_process_date           TIMESTAMP,
);

create table metrics
(
    metric_id               UUID NOT NULL,
    is_complete             BOOLEAN NOT NULL,
    type                    VARCHAR(50),
    date                    TIMESTAMP,
    doc_ref                 VARCHAR(50) NOT NULL,
    primary key (metric_id),
    foreign key (doc_ref) REFERENCES history (doc_ref)
);