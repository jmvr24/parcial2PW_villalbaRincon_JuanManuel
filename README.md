Estos son los queries necesarios para la db.

CREATE DATABASE parcial2_pw
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


INSERT INTO public.rol(rol_nombre)
	VALUES ('ROLE_RECTOR'),
	('ROLE_DOCENTE'),
	('ROLE_ESTUDIANTE');
