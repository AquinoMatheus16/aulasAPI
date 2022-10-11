-- Artista e música
select
	a.nome,
	m.nome
from
	artista a
inner join musica m on
	m.artista_id = a.id;



--Artistas e países que eles tocam
select
	distinct a.nome as artist,
	p.nome as pais
from
	artista a
inner join musica m on
	m.artista_id = a.id
inner join pais_musica pm on
	pm.musica_id = m.id
inner join pais p on
	pm.pais_id = p.id
order by
	p.nome;



--Top 10 músicas de determinado país
select
	m.nome as musica,
	p.nome as pais
from
	musica m
inner join pais_musica pm on
	pm.musica_id = m.id
inner join pais p on
	pm.pais_id = p.id
where
	p.nome like 'Brazil'
limit 10;



--Quantidade total de gêneros
select
	distinct count(*)
from
	genero;



--Quantidade de generos por artista
select
	a.nome,
	count(*) as qtd
from
	artista a
inner join artista_genero ag on
	ag.artista_id = a.id
group by
	a.id
order by
	qtd;



--Gêneros de determinado país
select
	distinct g.nome as genero,
	p.nome as pais
from
	genero g
inner join artista_genero ag on
	ag.genero_id = g.id
inner join artista a on
	ag.artista_id = a.id
inner join musica m on
	m.artista_id = a.id
inner join pais_musica pm on
	pm.musica_id = m.id
inner join pais p on
	pm.pais_id = p.id
where
	p.nome like 'Brazil';



--Quantidade de músicas por país
select
	distinct p.nome as pais,
	count(*) as qtd_musicas
from
	pais p
inner join pais_musica pm on
	pm.pais_id = p.id
inner join musica m on
	m.id = pm.musica_id
group by
	p.nome;



--Artista com a popularidade mais alta
select
	a.nome,
	MAX(a.popularidade) as popularidade
from
	artista a
group by
	a.nome;



--Música com maior quantidade de streams
select
	m.nome,
	MAX(pm.streams) as streams
from
	musica m
inner join pais_musica pm on
	m.id = pm.musica_id
group by m.nome;



--Quantidade de paises que cada musica toca
select
	m.nome,
	count(*)
from
	pais_musica pm
inner join musica m on
	m.id = pm.musica_id
group by
	pm.musica_id,
	m.nome;
