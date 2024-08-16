create table validation_process
(
    validation_process_id   UUID PRIMARY KEY,
    dir_ref                 VARCHAR(50) NOT NULL UNIQUE,
    is_success             BOOLEAN NOT NULL,
    total_doc_ref           VARCHAR(50),
    validation_process_date           TIMESTAMP
);

create table validation_file_history
(
    validation_id               UUID PRIMARY KEY,
    validation_process_id             UUID,
    file_name                    VARCHAR(50),
    doc_ref                 VARCHAR(50) NOT NULL,
    is_success             BOOLEAN NOT NULL,
    fail_fields               TEXT,
    validation_date                    TIMESTAMP,
    foreign key (validation_process_id) REFERENCES validation_process (validation_process_id)
);