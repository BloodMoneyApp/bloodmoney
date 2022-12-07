CREATE ROLE bloodmoney LOGIN
    PASSWORD 'bloodmoneypwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;

GRANT pg_monitor TO bloodmoney;
GRANT pg_read_all_settings TO bloodmoney;
GRANT pg_read_all_stats TO bloodmoney;
GRANT pg_signal_backend TO bloodmoney;
GRANT pg_stat_scan_tables TO bloodmoney;

CREATE TABLESPACE tablespace_bloodmoney
    OWNER tw
    LOCATION '/opt/postgresql/tablespace_bloodmoney';

ALTER TABLESPACE tablespace_bloodmoney
    OWNER TO bloodmoney;

CREATE DATABASE bloodmoney
    WITH OWNER = bloodmoney
    ENCODING = 'UTF8'
    TABLESPACE = tablespace_bloodmoney
    CONNECTION LIMIT = -1;

CREATE DATABASE bloodmoneyref
  WITH OWNER = bloodmoney
  ENCODING = 'UTF8'
  TABLESPACE = tablespace_bloodmoney
  CONNECTION LIMIT = -1;
