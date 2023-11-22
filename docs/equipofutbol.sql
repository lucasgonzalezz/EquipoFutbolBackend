-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 22-11-2023 a las 19:27:46
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `equipofutbol`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `ano_fundacion` date NOT NULL,
  `estadio` varchar(255) NOT NULL,
  `liga` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id`, `nombre`, `ciudad`, `ano_fundacion`, `estadio`, `liga`, `username`, `password`, `role`) VALUES
(1, 'Real Madrid', 'Madrid', '1902-03-06', 'Estadio Santiago Bernabéu', 'La Liga', 'realmadrid', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 0),
(2, 'FC Barcelona', 'Barcelona', '1899-10-22', 'Camp Nou', 'La Liga', 'fcbarcelona', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(3, 'AC Milan', 'Murcia', '1998-01-19', 'Giuseppe Meazza', 'MLS', 'AC Mur0', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(4, 'Paris Saint-Germain', 'Bilbao', '2011-08-24', 'Giuseppe Meazza', 'Ligue 1', 'ParBil1', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(5, 'Manchester City', 'Burgos', '1988-01-12', 'Etihad Stadium', 'Serie A', 'ManBur2', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(6, 'Ajax Amsterdam', 'Las Palmas de Gran Canaria', '1996-02-13', 'Wembley Stadium', 'MLS', 'AjaLas3', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(7, 'Liverpool FC', 'Las Palmas de Gran Canaria', '2005-06-19', 'Santiago Bernabéu', 'Major League Soccer', 'LivLas4', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(8, 'Tottenham Hotspur', 'Terrassa', '1984-02-10', 'Stadio Olimpico', 'J1 League', 'TotTer5', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(9, 'Real Madrid', 'Las Palmas de Gran Canaria', '2000-07-24', 'Maracanã', 'MLS', 'ReaLas6', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(10, 'Chelsea FC', 'Toledo', '2000-07-23', 'Santiago Bernabéu', 'Primeira Liga', 'CheTol7', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(11, 'Real Madrid', 'Burgos', '1988-01-31', 'Anfield', 'Argentine Primera División', 'ReaBur8', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1),
(12, 'Arsenal FC', 'Getafe', '2023-11-07', 'Signal Iduna Park', 'Ligue 1', 'ArsGet9', 'AA827BD694BD1418479BFEA6640C5EBC66863DCBA536203BA9C42ECD71A29336', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `posicion` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nacionalidad` varchar(255) NOT NULL,
  `id_equipo` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`id`, `nombre`, `apellido`, `fecha_nacimiento`, `posicion`, `nacionalidad`, `id_equipo`) VALUES
(1, 'Neymar', 'Griezmann', '2015-04-30', 'CAM', 'Sudáfrica', 2),
(2, 'Sergio', 'Neymar', '2014-11-11', 'SD', 'Suecia', 2),
(3, 'Kevin', 'Ronaldo', '1978-09-21', 'MD', 'Estados Unidos', 2),
(4, 'Erling', 'Ramos', '1985-02-15', 'SD', 'Bélgica', 2),
(5, 'Erling', 'Messi', '2019-04-14', 'CAM', 'México', 1),
(6, 'Harry', 'Ronaldo', '1978-05-04', 'CAD', 'Marruecos', 2),
(7, 'Cristiano', 'Ronaldo', '1983-07-06', 'LD', 'Austria', 1),
(8, 'Luka', 'Reus', '1994-03-24', 'MI', 'Sudáfrica', 2),
(9, 'Robert', 'Reus', '2002-11-23', 'SD', 'Marruecos', 2),
(10, 'Kevin', 'Mbappé', '1975-05-09', 'CAM', 'Nigeria', 1),
(11, 'Harry', 'Lewandowski', '1997-09-07', 'CAI', 'Japón', 7),
(12, 'Virgil', 'Neymar', '1999-09-05', 'CAI', 'Suiza', 11),
(13, 'Kevin', 'Lewandowski', '2000-05-18', 'DFC', 'Nigeria', 8),
(14, 'Mohamed', 'Pogba', '2016-01-20', 'CAD', 'Argentina', 6),
(15, 'Karim', 'Salah', '1976-04-13', 'MD', 'India', 12),
(16, 'Cristiano', 'Pogba', '2018-09-06', 'CAI', 'Australia', 9),
(17, 'Sergio', 'Van Dijk', '2013-02-12', 'CM', 'Reino Unido', 6),
(18, 'Antoine', 'Salah', '1992-11-04', 'EI', 'Croacia', 12),
(19, 'Antoine', 'Lewandowski', '1973-10-25', 'CAM', 'Brasil', 12),
(20, 'Mohamed', 'Lewandowski', '1975-02-16', 'POR', 'Países Bajos', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `miembro_cuerpo_tecnico`
--

CREATE TABLE `miembro_cuerpo_tecnico` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nacionalidad` varchar(255) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `id_equipo` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `miembro_cuerpo_tecnico`
--

INSERT INTO `miembro_cuerpo_tecnico` (`id`, `nombre`, `apellido`, `fecha_nacimiento`, `nacionalidad`, `titulo`, `id_equipo`) VALUES
(1, 'Pep', 'Tuchel', '1981-06-13', 'Nueva Zelanda', 'Médico Deportivo', 1),
(2, 'Jürgen', 'García', '2021-11-05', 'Portugal', 'Entrenador de Juveniles', 2),
(3, 'Lucien', 'Conte', '1982-04-06', 'Francia', 'Entrenador de Juveniles', 1),
(4, 'Mauricio', 'Klopp', '1971-04-11', 'Brasil', 'Nutricionista Deportivo', 2),
(5, 'Pep', 'Bielsa', '2012-04-18', 'Japón', 'Médico Deportivo', 2),
(6, 'Carlo', 'Valverde', '2001-08-20', 'Japón', 'Especialista en Rehabilitación', 2),
(7, 'Mauricio', 'García', '1973-03-21', 'Nueva Zelanda', 'Psicoterapeuta', 2),
(8, 'Pep', 'Bielsa', '2000-10-06', 'China', 'Fisioterapeuta Deportivo', 1),
(9, 'Jürgen', 'Benítez', '1985-05-04', 'Argentina', 'Asistente Técnico', 2),
(10, 'Roberto', 'Zidane', '2020-11-10', 'México', 'Analista Táctico', 2),
(11, 'Antonio', 'Klopp', '1994-08-12', 'Australia', 'Preparador Físico', 3),
(12, 'Diego', 'Conte', '2009-04-01', 'Suiza', 'Nutricionista Deportivo', 11),
(13, 'Rafa', 'Benítez', '1998-03-14', 'Países Bajos', 'Psicoterapeuta', 2),
(14, 'Jürgen', 'Bielsa', '1984-09-07', 'España', 'Médico Deportivo', 4),
(15, 'Simone', 'Solskjær', '2001-05-07', 'India', 'Entrenador', 11),
(16, 'Thomas', 'Simeone', '2015-09-18', 'Italia', 'Scout', 1),
(17, 'Carlo', 'Pochettino', '1995-05-29', 'Suiza', 'Fisioterapeuta', 5),
(18, 'José', 'Klopp', '2015-10-15', 'Brasil', 'Psicólogo Deportivo', 1),
(19, 'Roberto', 'Zidane', '1972-09-07', 'Argelia', 'Fisioterapeuta', 12),
(20, 'Pep', 'Pochettino', '2017-05-16', 'Argelia', 'Fisioterapeuta', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_equipo_id` (`id`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi60lqb7emposedyui33q42u1i` (`id_equipo`);

--
-- Indices de la tabla `miembro_cuerpo_tecnico`
--
ALTER TABLE `miembro_cuerpo_tecnico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK79c50j1dx4bh2wp1ce2vdc2mh` (`id_equipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `jugador`
--
ALTER TABLE `jugador`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `miembro_cuerpo_tecnico`
--
ALTER TABLE `miembro_cuerpo_tecnico`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `FKi60lqb7emposedyui33q42u1i` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`);

--
-- Filtros para la tabla `miembro_cuerpo_tecnico`
--
ALTER TABLE `miembro_cuerpo_tecnico`
  ADD CONSTRAINT `FK79c50j1dx4bh2wp1ce2vdc2mh` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
