--liquibase formatted sql
--changeset angryelizar:002_insert_values

-- Task statuses
INSERT INTO TASK_STATUSES(STATUS)
VALUES ('CREATED'),
       ('COMPLETED'),
       ('DELETED');

-- Authorities
INSERT INTO AUTHORITIES(AUTHORITY)
VALUES ('USER');

-- Users
-- Password - Qwerty123!
INSERT INTO USERS(EMAIL, USERNAME, PASSWORD, ENABLED, AUTHORITY_ID)
VALUES ('conovalov.elizar@gmail.com', 'angryelizar', '$2a$12$3ibZtYdAECCociSEafHe1eDGQGqGF2LsmREkHzALCv6IBFoaRaYbG', true, (select id from AUTHORITIES where AUTHORITY = 'USER'));

-- Tasks
INSERT INTO TASKS(TITLE, DESCRIPTION, AUTHOR, STATUS_ID)
VALUES ('Write a REST API for Mega', 'I need finish test task',
        (select email from USERS where USERNAME = 'angryelizar'),
        (select id from TASK_STATUSES where STATUS = 'CREATED'));

INSERT INTO TASKS(TITLE, DESCRIPTION, AUTHOR, STATUS_ID)
VALUES ('Implement caching with Redis', 'Cache task retrieval for optimization',
        (select email from USERS where USERNAME = 'angryelizar'),
        (select id from TASK_STATUSES where STATUS = 'COMPLETED'));

INSERT INTO TASKS(TITLE, DESCRIPTION, AUTHOR, STATUS_ID)
VALUES ('Refactor GlobalExceptionHandler', 'Improve error handling for the application',
        (select email from USERS where USERNAME = 'angryelizar'),
        (select id from TASK_STATUSES where STATUS = 'DELETED'));

INSERT INTO TASKS(TITLE, DESCRIPTION, AUTHOR, STATUS_ID)
VALUES ('Optimize database queries', 'Reduce execution time of SQL queries',
        (select email from USERS where USERNAME = 'angryelizar'),
        (select id from TASK_STATUSES where STATUS = 'CREATED'));
