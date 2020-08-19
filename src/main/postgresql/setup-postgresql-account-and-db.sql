CREATE ROLE bloodmoney LOGIN
    PASSWORD 'bloodmoneypwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
GRANT pg_monitor TO bloodmoney;
GRANT pg_read_all_settings TO bloodmoney;
GRANT pg_read_all_stats TO bloodmoney;
GRANT pg_signal_backend TO bloodmoney;
GRANT pg_stat_scan_tables TO bloodmoney;

CREATE ROLE bloodmoneyref LOGIN
    PASSWORD 'bloodmoneyrefpwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
GRANT pg_monitor TO bloodmoneyref;
GRANT pg_read_all_settings TO bloodmoneyref;
GRANT pg_read_all_stats TO bloodmoneyref;
GRANT pg_signal_backend TO bloodmoneyref;
GRANT pg_stat_scan_tables TO bloodmoneyref;


CREATE DATABASE bloodmoney
    WITH OWNER = bloodmoney
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoneyref
    WITH OWNER = bloodmoneyref
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


