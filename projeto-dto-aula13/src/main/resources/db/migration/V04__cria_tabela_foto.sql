CREATE TABLE foto (
	id_foto serial PRIMARY KEY,
	dados bytea,
	tipo varchar(100),
	nome varchar(100),
	id_funcionario bigint,
	FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);