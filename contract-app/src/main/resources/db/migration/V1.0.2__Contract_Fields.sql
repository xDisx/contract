alter table "xdisx-contract".contracts
drop column if exists contract_type;

ALTER TABLE "xdisx-contract".contracts
    ADD COLUMN if not exists customer_id NUMERIC NOT NULL default 70,
    ADD COLUMN if not exists device_code VARCHAR(255) NOT NULL default 'Dev12345xyz',
    ADD COLUMN if not exists device_type VARCHAR(50) NOT NULL default 'LAPTOP',
    ADD COLUMN if not exists price NUMERIC NOT NULL DEFAULT 123,
    ADD COLUMN if not exists product_id NUMERIC NOT NULL DEFAULT 7,
    ADD COLUMN if not exists period INT NOT NULL default 1,
    add column if not exists customer_name varchar(255) not null default 'Bob',
    add column if not exists acquisition_date DATE not null default CURRENT_DATE,
    add column if not exists contract_start_date DATE,
    add column if not exists contract_planned_end_date DATE,
    add column if not exists contract_status varchar(255) not null default 'CREATED';

