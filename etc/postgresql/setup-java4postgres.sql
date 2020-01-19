-- Role: java4postgres

-- DROP ROLE java4postgres;

CREATE ROLE java4postgres LOGIN
    PASSWORD 'java4postgrespwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
GRANT pg_monitor TO java4postgres;
GRANT pg_read_all_settings TO java4postgres;
GRANT pg_read_all_stats TO java4postgres;
GRANT pg_signal_backend TO java4postgres;
GRANT pg_stat_scan_tables TO java4postgres;


-- Database: java4postgres

-- DROP DATABASE java4postgres;
-- CONNECTION LIMIT = -1;

CREATE DATABASE java4postgres
    WITH OWNER = java4postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE ROLE tw LOGIN
    PASSWORD 'java4postgrespwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
GRANT pg_monitor TO tw;
GRANT pg_read_all_settings TO tw;
GRANT pg_read_all_stats TO tw;
GRANT pg_signal_backend TO tw;
GRANT pg_stat_scan_tables TO tw;


-- Database: java4postgres

-- DROP DATABASE java4postgres;
-- CONNECTION LIMIT = -1;

CREATE DATABASE tw
    WITH OWNER = tw
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
