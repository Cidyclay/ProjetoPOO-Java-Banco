-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 192.168.1.65:3306
-- Tempo de geração: 22/06/2023 às 07:04
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `test`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `conta_fisica`
--

CREATE TABLE `conta_fisica` (
  `id_conta_fisica` int(11) NOT NULL,
  `id_pessoa_fisica` int(11) NOT NULL,
  `saldo` decimal(10,2) DEFAULT 0.00,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `conta_fisica`
--

INSERT INTO `conta_fisica` (`id_conta_fisica`, `id_pessoa_fisica`, `saldo`, `status`) VALUES
(21, 21, 60.00, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `conta_juridica`
--

CREATE TABLE `conta_juridica` (
  `id_conta_juridica` int(11) NOT NULL,
  `id_pessoa_juridica` int(11) NOT NULL,
  `saldo` decimal(10,2) DEFAULT 0.00,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `conta_juridica`
--

INSERT INTO `conta_juridica` (`id_conta_juridica`, `id_pessoa_juridica`, `saldo`, `status`) VALUES
(13, 13, 700.00, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pessoa_fisica`
--

CREATE TABLE `pessoa_fisica` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `data_nascimento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pessoa_fisica`
--

INSERT INTO `pessoa_fisica` (`id`, `nome`, `cpf`, `data_nascimento`) VALUES
(21, 'Naruto', '12345678947', '2003-08-25');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pessoa_juridica`
--

CREATE TABLE `pessoa_juridica` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `mei` varchar(8) NOT NULL,
  `data_nascimento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pessoa_juridica`
--

INSERT INTO `pessoa_juridica` (`id`, `nome`, `cpf`, `mei`, `data_nascimento`) VALUES
(13, 'Jose', '12345678999', '12345678', '2005-07-20'),
(14, 'Jose', '12345678998', '12345678', '2005-07-20'),
(15, 'Jose', '12345678998', '12345678', '2005-07-20'),
(16, 'Jose', '12345678998', '12345678', '2005-07-20');

-- --------------------------------------------------------

--
-- Estrutura para tabela `transacao_fisica`
--

CREATE TABLE `transacao_fisica` (
  `id` int(11) NOT NULL,
  `id_conta` int(11) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `transacao_fisica`
--

INSERT INTO `transacao_fisica` (`id`, `id_conta`, `tipo`, `valor`, `data`) VALUES
(10, 21, 'Deposito', 80.00, '2023-06-19'),
(11, 21, 'Deposito', 100.00, '2025-06-19'),
(12, 21, 'Saque', 60.00, '2023-06-21'),
(13, 21, 'Saque', 60.00, '2023-06-22');

-- --------------------------------------------------------

--
-- Estrutura para tabela `transacao_juridica`
--

CREATE TABLE `transacao_juridica` (
  `id` int(11) NOT NULL,
  `id_conta` int(11) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `transacao_juridica`
--

INSERT INTO `transacao_juridica` (`id`, `id_conta`, `tipo`, `valor`, `data`) VALUES
(1, 13, 'Deposito', 100.00, '2023-06-22');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `conta_fisica`
--
ALTER TABLE `conta_fisica`
  ADD PRIMARY KEY (`id_conta_fisica`),
  ADD KEY `id_pessoa_fisica` (`id_pessoa_fisica`);

--
-- Índices de tabela `conta_juridica`
--
ALTER TABLE `conta_juridica`
  ADD PRIMARY KEY (`id_conta_juridica`),
  ADD KEY `id_pessoa_juridica` (`id_pessoa_juridica`);

--
-- Índices de tabela `pessoa_fisica`
--
ALTER TABLE `pessoa_fisica`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `pessoa_juridica`
--
ALTER TABLE `pessoa_juridica`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `transacao_fisica`
--
ALTER TABLE `transacao_fisica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_conta` (`id_conta`);

--
-- Índices de tabela `transacao_juridica`
--
ALTER TABLE `transacao_juridica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transacao_juridica_ibfk_1` (`id_conta`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `conta_fisica`
--
ALTER TABLE `conta_fisica`
  MODIFY `id_conta_fisica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de tabela `conta_juridica`
--
ALTER TABLE `conta_juridica`
  MODIFY `id_conta_juridica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `pessoa_fisica`
--
ALTER TABLE `pessoa_fisica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de tabela `pessoa_juridica`
--
ALTER TABLE `pessoa_juridica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de tabela `transacao_fisica`
--
ALTER TABLE `transacao_fisica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `transacao_juridica`
--
ALTER TABLE `transacao_juridica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `conta_fisica`
--
ALTER TABLE `conta_fisica`
  ADD CONSTRAINT `conta_fisica_ibfk_1` FOREIGN KEY (`id_pessoa_fisica`) REFERENCES `pessoa_fisica` (`id`);

--
-- Restrições para tabelas `conta_juridica`
--
ALTER TABLE `conta_juridica`
  ADD CONSTRAINT `conta_juridica_ibfk_1` FOREIGN KEY (`id_pessoa_juridica`) REFERENCES `pessoa_juridica` (`id`);

--
-- Restrições para tabelas `transacao_fisica`
--
ALTER TABLE `transacao_fisica`
  ADD CONSTRAINT `transacao_fisica_ibfk_1` FOREIGN KEY (`id_conta`) REFERENCES `conta_fisica` (`id_conta_fisica`) ON DELETE CASCADE;

--
-- Restrições para tabelas `transacao_juridica`
--
ALTER TABLE `transacao_juridica`
  ADD CONSTRAINT `transacao_juridica_ibfk_1` FOREIGN KEY (`id_conta`) REFERENCES `conta_juridica` (`id_conta_juridica`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
