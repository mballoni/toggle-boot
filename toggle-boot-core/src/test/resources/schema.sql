CREATE TABLE IF NOT EXISTS toggles (

    toggle_name VARCHAR(45) NOT NULL,
    active BIT DEFAULT 0,

    PRIMARY KEY (toggle_name)
);