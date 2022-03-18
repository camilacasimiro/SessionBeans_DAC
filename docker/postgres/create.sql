CREATE TABLE CLIENTE(
  nome varchar (25),
  cpf varchar (25),
  id serial,
  PRIMARY KEY (id)
);

CREATE TABLE PRODUTO(
   descricao varchar (25),
   valor decimal,
   id serial,
   PRIMARY KEY (id)
);

CREATE TABLE VENDA(
    produto int,
    cliente int,
    id serial,
    FOREIGN KEY (produto) REFERENCES PRODUTO(id) ON DELETE CASCADE,
    FOREIGN KEY (cliente) REFERENCES CLIENTE(id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);