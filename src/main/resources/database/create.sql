CREATE TABLE cidade
(
    id_cidade INT AUTO_INCREMENT NOT NULL,
    nome          VARCHAR(30)        NULL,
    estado            VARCHAR(2)         NULL,
    taxa          FLOAT              NOT NULL,
    CONSTRAINT pk_cidade PRIMARY KEY (id_cidade)
);

CREATE TABLE cliente
(
    id_cliente INT AUTO_INCREMENT NOT NULL,
    nome           VARCHAR(30)        NULL,
    telefone       VARCHAR(30)        NULL,
    endereco       VARCHAR(30)        NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente)
);

CREATE TABLE frete
(
    id_frete   INT AUTO_INCREMENT NOT NULL,
    id_cliente INT                NULL,
    id_cidade  INT                NULL,
    descricao      VARCHAR(30)        NULL,
    peso           FLOAT              NOT NULL,
    valor          FLOAT              NOT NULL,
    CONSTRAINT pk_frete PRIMARY KEY (id_frete)
);

ALTER TABLE frete
    ADD CONSTRAINT FK_FRETE_ON_CODIGO_CIDADE FOREIGN KEY (id_cidade) REFERENCES cidade (id_cidade);

ALTER TABLE frete
    ADD CONSTRAINT FK_FRETE_ON_CODIGO_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente);