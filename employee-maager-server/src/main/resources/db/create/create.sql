CREATE SCHEMA EMPLOYEE_MANAGERDB;
CREATE DATABASE EMPLOYEE_MANAGERDB;
CREATE LOGIN emp_mngr_usr WITH PASSWORD = 'admin';
CREATE USER file_builder_usr FOR LOGIN emp_mngr_usr;
ALTER AUTHORIZATION ON DATABASE::EMPLOYEE_MANAGERDB TO emp_mngr_usr;