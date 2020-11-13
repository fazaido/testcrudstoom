DROP TABLE endereco;
CREATE TABLE endereco(
	id BIGINT NOT NULL auto_increment,
	streetName varchar(500) NOT NULL,
	number int NOT NULL,
	complement varchar(250),
	neighbourhood varchar(150) NOT NULL,
	city varchar(150) NOT NULL,
	state varchar(150) NOT NULL,
	country varchar(150) NOT NULL,
	zipcode int NOT NULL,
	latitude double,
	longitude double,
	dataCriacao date NOT NULL,
	dataAtualizacao date
);