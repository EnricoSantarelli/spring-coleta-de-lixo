CREATE SEQUENCE TBL_USER_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE TBL_ADDRESS_SEQ START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE TBL_SCHEDULING_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE tbl_address (
    id INTEGER DEFAULT TBL_ADDRESS_SEQ.NEXTVAL PRIMARY KEY,
    street VARCHAR(255),
    num INTEGER,
    district VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    cep INTEGER
);

CREATE TABLE tbl_user (
    id INTEGER DEFAULT TBL_USER_SEQ.NEXTVAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(255) DEFAULT 'USER',
    address_id INTEGER,
    FOREIGN KEY (address_id) REFERENCES tbl_address(id)
);

CREATE TABLE tbl_scheduling (
    id INTEGER DEFAULT TBL_SCHEDULING_SEQ.NEXTVAL PRIMARY KEY,
    user_id INTEGER,
    timestamp INTEGER,
    status VARCHAR(255) DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES tbl_user(id)
);
