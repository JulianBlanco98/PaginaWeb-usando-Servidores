-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: tw
-- ------------------------------------------------------
-- Server version	5.5.5-10.11.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `idUsuario` int(11) NOT NULL,
  `idRuta` int(11) NOT NULL,
  `fechaReserva` date NOT NULL,
  `numeroPersonas` int(11) NOT NULL CHECK (`numeroPersonas` >= 1 and `numeroPersonas` <= 10),
  PRIMARY KEY (`idUsuario`,`idRuta`,`fechaReserva`),
  KEY `idRuta` (`idRuta`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`idRuta`) REFERENCES `ruta` (`idRuta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (2,1,'2023-04-05',2),(2,3,'2023-04-06',4),(3,2,'2023-04-07',3),(4,4,'2023-04-08',1),(7,1,'2023-04-27',10),(7,1,'2023-04-30',6),(7,2,'2023-04-27',10),(12,2,'2023-04-27',4);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ruta` (
  `idRuta` int(11) NOT NULL AUTO_INCREMENT,
  `pathImagen` varchar(255) NOT NULL,
  `fechaIncorporacion` date NOT NULL,
  `maximoUsuario` int(11) NOT NULL,
  `dificultad` int(11) NOT NULL,
  `metros` int(11) NOT NULL,
  `nombreRuta` varchar(255) NOT NULL,
  PRIMARY KEY (`idRuta`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (1,'../img/merida.jpg','2022-01-01',20,3,5000,'Mérida - Río Guadiana - Río Matachel'),(2,'../img/monesterio.jpg','2022-02-14',15,2,3500,'Vía de la Plata: Monesterio - Los Santos de Maimona'),(3,'../img/olivenza.jpg','2022-05-03',30,4,8000,'Olivenza - San Jorge de Alor'),(4,'../img/plasencia.jpg','2022-07-22',25,5,10000,'Paseo Fluvial Plasencia'),(5,'../img/torremejia.jpg','2022-07-22',10,2,6000,'Torremejía - Arroyo de San Serván'),(6,'../img/ruta1.jpg','2023-04-23',100,4,17000,'Cordel del Cerro del Gato');
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `tipo` varchar(10) DEFAULT 'user',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'Juan','Pérez García','juanperez@email.com','juanpg','contra1','user'),(3,'María','López Martínez','marialopez@email.com','marialm','contra2','user'),(4,'Carlos','González Sánchez','carlosgonzalez@email.com','carlosgs','contra3','user'),(5,'Ana','Martínez Ruiz','anamartinez@email.com','anamr','contra4','user'),(7,'a','a','a','a','a','user'),(9,'admin','admin','admin@gmail.com','admin','admin','admin'),(11,'d','d','d@gmail.com','d','e','user'),(12,'e','e','e@gmail.com','e','e','user'),(13,'f','f','f@gmail.com','f','f','user');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoraciones`
--

DROP TABLE IF EXISTS `valoraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valoraciones` (
  `idValoracion` int(11) NOT NULL AUTO_INCREMENT,
  `idRuta` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `comentario` varchar(255) NOT NULL,
  `valoracion` int(11) NOT NULL,
  PRIMARY KEY (`idValoracion`),
  KEY `idRuta` (`idRuta`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `valoraciones_ibfk_1` FOREIGN KEY (`idRuta`) REFERENCES `ruta` (`idRuta`),
  CONSTRAINT `valoraciones_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoraciones`
--

LOCK TABLES `valoraciones` WRITE;
/*!40000 ALTER TABLE `valoraciones` DISABLE KEYS */;
INSERT INTO `valoraciones` VALUES (1,1,2,'Una ruta muy bonita',4),(2,3,2,'Muy complicada, pero merece la pena',3),(3,2,3,'No es para tanto',2),(4,4,4,'Una ruta muy sencilla pero relajante',5),(5,1,7,'prueba',5),(6,2,7,'prueba de la ruta 2',1),(7,3,7,'prueba de la ruta 3',3);
/*!40000 ALTER TABLE `valoraciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tw'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-27 19:41:38
