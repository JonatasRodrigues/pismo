DROP SCHEMA IF EXISTS pismo_db cascade;

CREATE SCHEMA IF NOT EXISTS pismo_db;
USE pismo_db;

CREATE TABLE accounts(
account_id INT not null AUTO_INCREMENT,
document_number VARCHAR(100) NOT NULL,
constraint pk_accounts PRIMARY KEY (account_id));

CREATE TABLE operationsTypes(
operation_type_id INT not null AUTO_INCREMENT,
descriptions VARCHAR(255) NOT NULL,
constraint pk_operations_types PRIMARY KEY (operation_type_id));

CREATE TABLE transactions(
transaction_id int NOT NULL AUTO_INCREMENT,
operation_type_id INT NOT NULL,
account_id INT NOT NULL,
amount DECIMAL(10,2) NOT NULL,
event_date TIMESTAMP NOT NULL,
constraint pk_transactions PRIMARY KEY (transaction_id),
constraint fk_operations_types FOREIGN KEY (operation_type_id) REFERENCES operationsTypes(operation_type_id),
constraint fk_accounts FOREIGN KEY (account_id) REFERENCES accounts(account_id));

insert into operationsTypes(operation_type_id, descriptions) values (1, 'COMPRA A VISTA');
insert into operationsTypes(operation_type_id, descriptions) values (2, 'COMPRA PARCELADA');
insert into operationsTypes(operation_type_id, descriptions) values (3, 'SAQUE');
insert into operationsTypes(operation_type_id, descriptions) values (4, 'PAGAMENTO');

