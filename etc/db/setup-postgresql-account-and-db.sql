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
    WITH OWNER = bloodmoneyref
    ENCODING = 'UTF8'
    TABLESPACE = tablespace_bloodmoney
    CONNECTION LIMIT = -1;






CREATE ROLE jakartaee_petclinic LOGIN
    PASSWORD 'jakartaee_petclinicpwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
GRANT pg_monitor TO jakartaee_petclinic;
GRANT pg_read_all_settings TO jakartaee_petclinic;
GRANT pg_read_all_stats TO jakartaee_petclinic;
GRANT pg_signal_backend TO jakartaee_petclinic;
GRANT pg_stat_scan_tables TO jakartaee_petclinic;




-- Database: jakartaee_petclinic

-- DROP DATABASE jakartaee_petclinic;
-- CONNECTION LIMIT = -1;

CREATE DATABASE jakartaee_petclinic
    WITH OWNER = jakartaee_petclinic
    ENCODING = 'UTF8'
    TABLESPACE = tablespace_jakartaee_petclinic
    CONNECTION LIMIT = -1;


