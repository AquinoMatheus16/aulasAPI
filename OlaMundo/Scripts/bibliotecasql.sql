select l.titulo, c.nome from livro l, categoria c order by c.nome asc;

select a.nome , count(a.nome)as qtd_livro from autor a inner join livro_autor la on a.id = la.id_livro group by a.nome;