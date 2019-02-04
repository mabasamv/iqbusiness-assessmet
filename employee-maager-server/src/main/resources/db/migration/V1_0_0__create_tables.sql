CREATE TABLE employee-details (
    employee_id BIGINT IDENTITY(1,1),
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    CONSTRAINT employee-details_pk PRIMARY KEY CLUSTERED (employee_id)
);

GO