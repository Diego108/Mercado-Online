CREATE TABLE pessoa(
	id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	sobrenome VARCHAR(50) NOT NULL,
	cpf_cnpj BIGINT(11) NULL,
	data_nascimento DATE NOT NULL,
	tipo_usuario CHAR(1) NOT NULL,
	ativo TINYINT(1) NOT NULL,
	logradouro VARCHAR(200) NULL,
	complemento VARCHAR(50) NULL,
	referencia VARCHAR(50) NULL,
	tipo_residencia CHAR(1) NULL,
	numero BIGINT(10) NULL
) ENGINE=InnoDB DEFAULT CHARSET=Utf8;


INSERT INTO pessoa(nome, sobrenome, cpf_cnpj, data_nascimento, tipo_usuario, ativo, logradouro, complemento, referencia, tipo_residencia, numero) 
VALUES (
	'Diego', 'de Jesus Cordeiro', 42619064813, '1995-08-19', 0, 0, 'Rua Milton Patricio de Oliveira', '', 'Escola Santa Maria', 0, 74
)