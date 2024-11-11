CREATE TABLE conta (
    numeroConta int AUTO_INCREMENT not null primary key,
    nome varchar(255) not null,
    saldoConta numeric(18,2),
    tipoConta varchar(255) not null
);