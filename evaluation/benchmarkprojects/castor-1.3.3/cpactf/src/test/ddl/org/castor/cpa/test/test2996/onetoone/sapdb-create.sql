create table test2996_onetoone_address(
    id INTEGER PRIMARY KEY NOT NULL
)
//

create table test2996_onetoone_employee(
    id         INTEGER PRIMARY KEY NOT NULL,
    address_id INTEGER NOT NULL,
    FOREIGN KEY fk_test2996_onetoone_ea (address_id)
      REFERENCES test2996_onetoone_address(id)
)
//
