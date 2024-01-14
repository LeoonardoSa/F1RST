CREATE TABLE cliente (
    id SERIAL NOT NULL,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(80) NOT NULL,
    documento VARCHAR(14) NOT NULL,
    data_entrada DATE,

    CONSTRAINT pk_cliente PRIMARY KEY (id)
)