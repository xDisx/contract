create table if not exists "xdisx-contract".contracts (
    "id" numeric(10) primary key,
    "contract_type" varchar(32) not null ,
    "created" timestamp not null ,
    "modified" timestamp not null ,
    "version" bigint not null
);

CREATE SEQUENCE contract_id_seq START 1;