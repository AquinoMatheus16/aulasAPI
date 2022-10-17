CREATE TABLE categoria (
    id serial PRIMARY KEY,
	nome varchar(30) NOT NULL,
	descricao varchar(200)
);

CREATE TABLE associado(
	id serial PRIMARY KEY,
	nome varchar(50) NOT NULL,
	telefone varchar(15) NOT NULL,
	cpf varchar(11) NOT NULL,
	logradouro varchar(80) NOT NULL,
	numero varchar(10) NOT NULL,
	complemento varchar(30) NOT NULL,
	bairro varchar(50) NOT NULL,
	cidade varchar(50) NOT NULL,
	estado varchar(2) NOT NULL
);

CREATE TABLE autor(
	id serial PRIMARY KEY,
	nome varchar(50) NOT NULL
);

CREATE TABLE emprestimo(
	id serial PRIMARY KEY,
	associado int REFERENCES associado(id), 
	data_emprestimo date
);

CREATE TABLE livro (
    id serial PRIMARY KEY,
	titulo varchar(40) NOT NULL,
	categoria int REFERENCES categoria(id),
	isbn varchar(20) NOT NULL UNIQUE,
	dataPublicacao date
);

CREATE TABLE livro_autor(
	id_livro int REFERENCES livro(id),
	id_autor int REFERENCES autor(id)
);

CREATE TABLE emprestimo_livro(
	id serial PRIMARY KEY,
	id_emprestimo int REFERENCES emprestimo(id),
	id_livro int REFERENCES livro(id),
	observacoes_ato_emprestimo varchar(200)
);
