CREATE TABLE categoria (
   id_categoria serial PRIMARY KEY,
   nome varchar(20) NOT NULL,
   descricao varchar(200)
);

CREATE TABLE livro (
  id_livro serial PRIMARY KEY,
  titulo varchar(40) NOT NULL,
  isbn varchar(20) NOT NULL UNIQUE,
  data_publicacao date,
  id_categoria int REFERENCES categoria(id_categoria)
);
