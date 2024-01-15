CREATE TABLE conta (
  id SERIAL NOT NULL,
  cliente_id INT NOT NULL,
  banco VARCHAR(255) NOT NULL,
  agencia VARCHAR(255) NOT NULL,
  numero VARCHAR(255) NOT NULL,
  status VARCHAR(10) NOT NULL,

  CONSTRAINT pk_conta PRIMARY KEY (id),
  CONSTRAINT fk_conta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);