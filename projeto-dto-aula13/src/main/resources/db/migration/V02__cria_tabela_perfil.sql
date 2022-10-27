CREATE TABLE perfil (
id_perfil serial PRIMARY KEY,
nome varchar(40)
);

CREATE TABLE usuario_perfil (
id_usuario int REFERENCES usuario (id_usuario),
id_perfil int REFERENCES perfil(id_perfil),
data_criacao date,
CONSTRAINT pk_usuario_perfil PRIMARY KEY (id_usuario,
id_perfil)
);

INSERT
	INTO
	perfil (id_perfil,
	nome)
VALUES
(1,
'ROLE_ADMIN'),
(2,
'ROLE_USER');