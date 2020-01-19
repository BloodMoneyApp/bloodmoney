-- Role: bloodmoney

-- DROP ROLE bloodmoney;

-- DROP DATABASE bloodmoney;
-- CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney_default
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney_developing
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney_doc
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney_testing
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney_qa
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney_heroku
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoney_travis
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


