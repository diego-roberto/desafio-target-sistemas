-- Tabela Estado
CREATE TABLE Estado (
        id_estado SERIAL PRIMARY KEY,     -- chave primária
        uf CHAR(2) NOT NULL,              -- sigla do estado
        nome_estado VARCHAR(50) NOT NULL  -- nome completo do estado
);

-- Tabela Tipo_Telefone
CREATE TABLE Tipo_Telefone (
       id_tipo_telefone SERIAL PRIMARY KEY, -- chave primária
       descricao VARCHAR(50) NOT NULL       -- descrição do tipo de telefone
);

-- Tabela Cliente
CREATE TABLE Cliente (
     id_cliente SERIAL PRIMARY KEY,       -- chave primária
     razao_social VARCHAR(100) NOT NULL,  -- nome ou razão social do cliente
     id_estado INT NOT NULL,              -- chave estrangeira para a tabela Estado
     CONSTRAINT fk_cliente_estado FOREIGN KEY (id_estado) REFERENCES Estado(id_estado)
);

-- Tabela Telefone
CREATE TABLE Telefone (
      id_telefone SERIAL PRIMARY KEY,         -- chave primária
      numero_telefone VARCHAR(15) NOT NULL,   -- número de telefone
      id_cliente INT NOT NULL,                -- chave estrangeira para a tabela Cliente
      id_tipo_telefone INT NOT NULL,          -- chave estrangeira para a tabela Tipo_Telefone
      CONSTRAINT fk_telefone_cliente FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
      CONSTRAINT fk_telefone_tipo FOREIGN KEY (id_tipo_telefone) REFERENCES Tipo_Telefone(id_tipo_telefone)
);
