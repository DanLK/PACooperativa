CREATE DATABASE  IF NOT EXISTS `cooperativa` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cooperativa`;
-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: localhost    Database: cooperativa
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `pedido_id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_registro` date NOT NULL,
  `total` varchar(45) NOT NULL,
  `pedidoStatus_pedidoStatus_id` int(11) NOT NULL,
  `usuario_usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  UNIQUE KEY `idPedido_UNIQUE` (`pedido_id`),
  KEY `fk_pedido_pedidoStatus_idx` (`pedidoStatus_pedidoStatus_id`),
  KEY `fk_pedido_usuario1_idx` (`usuario_usuario_id`),
  CONSTRAINT `fk_pedido_pedidoStatus` FOREIGN KEY (`pedidoStatus_pedidoStatus_id`) REFERENCES `pedido_status` (`pedidoStatus_id`),
  CONSTRAINT `fk_pedido_usuario1` FOREIGN KEY (`usuario_usuario_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_status`
--

DROP TABLE IF EXISTS `pedido_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_status` (
  `pedidoStatus_id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`pedidoStatus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_status`
--

LOCK TABLES `pedido_status` WRITE;
/*!40000 ALTER TABLE `pedido_status` DISABLE KEYS */;
INSERT INTO `pedido_status` VALUES (1,'Enviado (en espera de pago)'),(2,'Pagado'),(3,'Entregado');
/*!40000 ALTER TABLE `pedido_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permiso` (
  `permiso_id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`permiso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `producto_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `contenido` varchar(45) DEFAULT NULL,
  `precio` float NOT NULL,
  `departamento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`producto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (7,'Paquetes verdes grandes','2',140,'Frutas y verduras'),(8,'Paquetes verdes chicos','2',90,'Frutas y verduras'),(9,'Zarzamora','175 g',35,'Frutas y verduras'),(10,'Frambuesa','175 g',35,'Frutas y verduras'),(11,'Fresa','475 g',50,'Frutas y verduras'),(12,'Higo','400 g',40,'Frutas y verduras'),(13,'Blueberry','175 g',45,'Frutas y verduras'),(14,'Huevo','24 pzas',92,'Huevo y lácteos'),(15,'Queso panela','1/2 kg',58,'Huevo y lácteos'),(16,'Queso oaxaca','1/2 kg',58,'Huevo y lácteos'),(17,'Requesón solo','250 g',50,'Huevo y lácteos'),(18,'Cotija + requesón','250 g',110,'Huevo y lácteos'),(19,'Doble crema + requesón','250 g',100,'Huevo y lácteos'),(20,'Asadero + requesón','250 g',115,'Huevo y lácteos'),(21,'Cajeta','300 ml',70,'Huevo y lácteos'),(22,'Leche entera Pascual','1 L',16,'Huevo y lácteos'),(23,'Queso chihuahua','400 g',74,'Huevo y lácteos'),(24,'Yogurt natural','1 L',33,'Huevo y lácteos'),(25,'Leche fresca pasteurizada','1 L',32,'Huevo y lácteos'),(26,'Frasco retornable de leche',NULL,-10,'Huevo y lácteos'),(27,'Tortilla de maís criollo','12',25,'Grano, harina y pan'),(28,'Arroz blanco','1 kg',32,'Grano, harina y pan'),(29,'Arroz integral','1 kg ',34,'Grano, harina y pan'),(30,'Pan baguette grande','60 cm',52,'Grano, harina y pan'),(31,'Pan baguette chico','30 cm',30,'Grano, harina y pan'),(32,'Roles canela/glaseados',NULL,15,'Grano, harina y pan'),(33,'Carteritas de guayaba',NULL,21,'Grano, harina y pan'),(34,'Chocolatín',NULL,21,'Grano, harina y pan'),(35,'Alfajores caseros ','8 pzas',75,'Grano, harina y pan'),(36,'Alfajores caseros','2 pzas',19,'Grano, harina y pan'),(37,'Amaranto tostado','250 g',13,'Grano, harina y pan'),(38,'Harina de amaranto','250 g',23,'Grano, harina y pan'),(39,'Megabarra con piñón','1 pz',66,'Grano, harina y pan'),(40,'Barras de amaranto sabor','10 pz',42,'Grano, harina y pan'),(41,'Obleas de amaranto','4 pz',22,'Grano, harina y pan'),(42,'Churritos de amaranto','125 g',11,'Grano, harina y pan'),(43,'Miel unifloral','1080 g',135,'Cafetería y abarrotes'),(44,'Miel unifloral ','610 g',75,'Cafetería y abarrotes'),(45,'Miel orgánica','850 g',140,'Cafetería y abarrotes'),(46,'Miel de maguey','250 g',75,'Cafetería y abarrotes'),(47,'Paletas de propoleo y miel','3',12,'Cafetería y abarrotes'),(48,'Café en grano molido','1/2 kg',80,'Cafetería y abarrotes'),(49,'Salsa picante chiltemaca',NULL,40,'Cafetería y abarrotes'),(50,'Cerveza artesanal Dängo',NULL,36,'Cafetería y abarrotes'),(51,'Soda artesanal fermentada',NULL,82,'Cafetería y abarrotes'),(52,'Envase retornable','',-13,'Cafetería y abarrotes'),(53,'Lavatrastes','4 L',128,'Limpieza del hogar'),(54,'Lavatrastes','20 L',540,'Limpieza del hogar'),(55,'Detergente liq. ropa','4 L',86,'Limpieza del hogar'),(56,'Detergente liq. ropa','20 L',350,'Limpieza del hogar'),(57,'Suavizante de ropa','4 L',85,'Limpieza del hogar'),(58,'Suavizante de ropa','20 L',320,'Limpieza del hogar'),(59,'Pinol concentrado','4 L',85,'Limpieza del hogar'),(60,'Pinol concentrado','20 L',250,'Limpieza del hogar'),(61,'Jabon liq de manos','4 L',97,'Limpieza del hogar'),(62,'Jabon liq de manos','20 L',374,'Limpieza del hogar'),(63,'Cloro al 6%','4 L',63,'Limpieza del hogar'),(64,'Cloro al 6%','20 L',246,'Limpieza del hogar'),(65,'Filete de Dorado','1/2 kg',70,'Productos del mar'),(66,'Filete de Bandera','1/2 kg',78,'Productos del mar'),(67,'Filete de Jurel','1/2 kg',55,'Productos del mar'),(68,'Filete de Mero','1/2 kg',218,'Productos del mar'),(69,'Filete de Huachinango','1/2 kg',258,'Productos del mar'),(70,'Filete de Salmón','1/2 kg',155,'Productos del mar'),(71,'Picado para ceviche dorado','1/2 kg',70,'Productos del mar'),(72,'Picado para ceviche manta','1/2 kg',58,'Productos del mar'),(73,'Pechuga de bandera','1/2 kg',35,'Productos del mar'),(74,'Camaron pacotilla','1/2 kg',112,'Productos del mar'),(75,'Camaron cristal mediano','1/2 kg',112,'Productos del mar'),(76,'Barritas de pescado','1/2 kg',45,'Productos del mar'),(77,'Shampoo de romero','500 ml',84,'Productos de ocasión'),(78,'Enjuague de romero','500 ml',84,'Productos de ocasión'),(79,'Shampoo de 15 hierbas','185 ml',75,'Productos de ocasión'),(80,'Ungüento herbal (reumático)','60 ml',78,'Productos de ocasión'),(81,'Shampoo corporal cítrico',NULL,115,'Productos de ocasión'),(82,'Ungüento herbal (dolor de cabeza)',NULL,57,'Productos de ocasión'),(83,'Respirasol',NULL,120,'Productos de ocasión'),(84,'Gel refrescante para piernas','500 ml',315,'Productos de ocasión'),(85,'Oleogel contorno de ojos','5 g',80,'Productos de ocasión'),(86,'Exfoliante facial','20 g',60,'Productos de ocasión'),(87,'Crema corporal','245 g',120,'Productos de ocasión'),(88,'Gel limpiador facial','120 g',105,'Productos de ocasión');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_pedido`
--

DROP TABLE IF EXISTS `producto_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_pedido` (
  `productoPedido_id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `pedido_pedido_id` int(11) NOT NULL,
  `producto_producto_id` int(11) NOT NULL,
  PRIMARY KEY (`productoPedido_id`),
  UNIQUE KEY `productoPedido_id_UNIQUE` (`productoPedido_id`),
  KEY `fk_productoPedido_pedido1_idx` (`pedido_pedido_id`),
  KEY `fk_productoPedido_producto1_idx` (`producto_producto_id`),
  CONSTRAINT `fk_productoPedido_pedido1` FOREIGN KEY (`pedido_pedido_id`) REFERENCES `pedido` (`pedido_id`),
  CONSTRAINT `fk_productoPedido_producto1` FOREIGN KEY (`producto_producto_id`) REFERENCES `producto` (`producto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_pedido`
--

LOCK TABLES `producto_pedido` WRITE;
/*!40000 ALTER TABLE `producto_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `rol_id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador'),(2,'Socio');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_permiso`
--

DROP TABLE IF EXISTS `rol_permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_permiso` (
  `permiso_permiso_id` int(11) NOT NULL,
  `rol_rol_id` int(11) NOT NULL,
  PRIMARY KEY (`permiso_permiso_id`,`rol_rol_id`),
  KEY `fk_rolPermiso_permiso1_idx` (`permiso_permiso_id`),
  KEY `fk_rolPermiso_rol1_idx` (`rol_rol_id`),
  CONSTRAINT `fk_rolPermiso_permiso1` FOREIGN KEY (`permiso_permiso_id`) REFERENCES `permiso` (`permiso_id`),
  CONSTRAINT `fk_rolPermiso_rol1` FOREIGN KEY (`rol_rol_id`) REFERENCES `rol` (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_permiso`
--

LOCK TABLES `rol_permiso` WRITE;
/*!40000 ALTER TABLE `rol_permiso` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol_permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `rol_rol_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `usuario_id_UNIQUE` (`usuario_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_usuario_rol1_idx` (`rol_rol_id`),
  CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`rol_rol_id`) REFERENCES `rol` (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'Daniela','Loustalot Knapp','DanielaL','root','daniloustalot95@gmail.com',NULL,1),(5,'Saúl Iván','Rivas Vega','SaulIRV','saul1234','saul.ivan.rivas.vega@gmail.com',NULL,1),(6,'Diego','Isla López','DiegoIsla','1234diego','dislalopez@gmail.com','45645324',1),(7,'Juan Alonso','Rodríguez Fuentes','Juan_Rodriguez','juanitobigotes','juan.rod@gmail.com','55555554',2),(8,'Ana Laura','Ponce Jiménez','Ana_lau','a1n2a3','a.laura@gmail.com',NULL,2),(9,'Roberto','Álvarez Ramírez','Rob125','robalram35',NULL,'56789403',2),(10,'Sandra','Mendelson Pérez','Sandy_Mendelson','SandyLaArdillita','sandrita@gmail.com','55980327',2),(11,'José Arcadio','Buendía','JABuendia','macondo_bello','josearcadio@hotmail.com','54321234',2),(12,'Úrsula','Iguarán','Ursulita_Iguaran','ursu_9034','ursula_9034@yahoo.com',NULL,2),(13,'Santa Sofía','de la Piedad','Sofia_Piedad','sofia4567','santasofi@yahoo.com','76563423',2),(14,'Pilar','Ternera','Pilar_Ternera_24','leolascartas123',NULL,'55579812',2),(15,'Fernanda ','del Carpio','Fer_delCarpio','fer3456','fer.del.carpio@gmail.com','55732516',2),(16,'Remedios','Moscote','Remedios_','remoscote','remedios.moscote@hotmail.com','57843726',2),(17,'Mauricio','Babilonia','Mauricio_Babilonia','mariposas_amarillas','mauricio.mariposas@gmail.com','22234627',2),(18,'Aureliano','Babilonia','Aureliano_B','babilonia_aureliano','a.babilonia@hotmail.com','56478921',2),(19,'Aureliano José','Buendía','Pepe.Buendia','ajbdia.1416','buendia_a_j@gmail.com','55463729',2),(21,'Helga','Pataki','HelgaP','arnold_mylove4e','helga.loves.arnold@hotmail.com','67488273',2),(22,'Arnold P','Shortman','Arnold_Short','cabezadebalon11','arnie_shortman@gmail.com','55564738',2),(24,'Gerald Martin','Johanssen','Gerald_Joh','gerald33','gerald.johanssen@yahoo.com',NULL,2),(25,'Harold','Berman','Harold_B','haroldisthebest',NULL,'55432456',2),(26,'Bob E.','Pantalones Cuadrados','Spongebob','square_pantss','bob_esponja@gmail.com','54679822',2),(27,'Juvenal','Urbino','Juvenal_Urbino','juve.1920','doctor.juvenal.urbino@gmail.com','76383367',2),(33,'Florentino','Ariza','Floren.Ariza','flarizaFAFA',NULL,'56873927',2),(34,'Fermina','Daza','DazaFermi<3','dazafermina...','fermina.daza.123@gmail.com','5533229911',2),(35,'Lorenzo','Daza','LorenDaza','dazaloren56','lorenzo.daza@gmail.com','56738374',2),(36,'Hildebranda','Sánchez','Hilde_Sanchez','hildesanchez','h.sanchez.ildebranda@hotmail.com','56346276',2),(37,'Winston','Smith','W.Smith','smith_abajoelhermanomayor','smith_winston@gmail.com','55667788',2),(38,'Emmanuel','Goldstein','EGoldstein','lahermandad','e_goldstein@hotmail.com','56590161',2),(40,'Lenina','Crowne','Lenina.Crowne','leninacrow98','lenina.crowne@gmail.com','22287637',2),(41,'Ángela','Vicario','Angela_Vicario','angelavica',NULL,'56748392',2),(45,'Santiago','Nassar','Santi.Nassar','nassantiago1357','el_santi@gmail.com','59039484',2),(46,'Tita','de la Garza','Tita.Garza','titalatitita',NULL,NULL,2),(47,'Rosaura','de la Garza','Rosaura.Garza','rosauradlg_09','delagarza.r@hotmail.com',NULL,2),(48,'Gertrudis','de la Garza','Gertrudis.Garza','ggarza_.987','gertrudis_garza@gmail.com','56894383',2),(49,'Pedro','Muzquiz','Pedro_Muzquiz','peter_muzq',NULL,'56789012',2),(50,'Juan','de la Garza','Juan.Garza','juanito_garzaxx','delagarza_juan@gmail.com','2220938782',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-04 13:08:06
