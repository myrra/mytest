--<ScriptOptions statementTerminator=";"/>

ALTER TABLE USERS DROP CONSTRAINT SQL160317214626880;

ALTER TABLE JOBS DROP CONSTRAINT SQL160418203114500;

DROP INDEX SQL160317214626880;

DROP INDEX SQL160418203114500;

DROP TABLE USERS;

DROP TABLE JOBS;

