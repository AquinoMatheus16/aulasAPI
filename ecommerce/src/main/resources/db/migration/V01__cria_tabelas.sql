CREATE TABLE categoria (
id_categoria serial PRIMARY KEY,
nome varchar(30) NOT NULL UNIQUE,
descricao varchar(200)
);

CREATE TABLE produto (
id_produto serial PRIMARY KEY,
nome varchar(30) NOT NULL UNIQUE,
descricao varchar(200),
qtd_estoque int,
data_cadastro date,
valor_unitario REAL NOT NULL,
imagem bytea,
id_categoria int NOT NULL REFERENCES categoria(id_categoria)
);

CREATE TABLE endereco (
id_endereco serial PRIMARY KEY,
cep varchar(8) NOT NULL, 
rua varchar(80) NOT NULL,
bairro varchar(50) NOT NULL, 
cidade varchar(80) NOT NULL, 
numero varchar(20) NOT NULL,
complemento varchar(80),
uf varchar(2) NOT NULL
);

CREATE TABLE cliente (
id_cliente serial PRIMARY KEY,
nome_completo varchar(50) NOT NULL,
email varchar(80) NOT NULL UNIQUE,
cpf varchar(11) NOT NULL UNIQUE, 
telefone varchar(40) NOT NULL,
data_nascimento date,
id_endereco int NOT NULL REFERENCES endereco(id_endereco)
);

CREATE TABLE pedido (
id_pedido serial PRIMARY KEY,
data_pedido date NOT NULL,
data_entrega date,
data_envio date,
status varchar(1) NOT NULL,
valor_total REAL NOT NULL,
id_cliente int NOT NULL REFERENCES cliente(id_cliente)
);

CREATE TABLE item_pedido (
id_item_pedido serial PRIMARY KEY,
quantidade int NOT NULL,
preco_venda REAL NOT NULL,
percentual_desconto REAL NOT NULL,
valor_bruto REAL NOT NULL,
valor_liquido REAL NOT NULL, 
id_produto int NOT NULL REFERENCES produto(id_produto),
id_pedido int NOT NULL REFERENCES pedido(id_pedido)
);