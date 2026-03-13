-- MySQL dump 10.13  Distrib 9.5.0, for macos15 (x86_64)
--
-- Host: localhost    Database: crud_db
-- ------------------------------------------------------
-- Server version	9.5.0

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '90ba4a46-09c3-11f1-80ee-c1b4412a11d3:1-45';

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `created_at`, `description`, `image_url`, `name`, `price`, `category`, `original_price`, `rating`, `sales_count`) VALUES (1,'2026-02-14 19:45:02.479242','RGB 背光，青轴，全键无冲','https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?w=400','极光机械键盘',2999,'',NULL,NULL,NULL),(2,'2026-02-14 19:45:02.479261','主动降噪 30h 续航','https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400','无线降噪耳机',599,'',NULL,NULL,NULL),(3,'2026-02-14 19:45:02.479266','20000mAh 双向快充','https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?w=400','便携充电宝',1299,'',NULL,NULL,NULL),(4,'2026-02-14 19:45:02.479271','心率血氧 50米防水','https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400','智能手表',899,'',NULL,NULL,NULL),(5,'2026-02-14 19:45:02.479275','人体工学 静音按键','https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400','无线鼠标',89,'',NULL,NULL,NULL),(6,'2026-02-14 19:57:58.426068','确实是好东西','https://cdn.bigmodel.cn/static/platform/images/home/V2/tool_section_content6.png','好东西',888,'',NULL,NULL,NULL),(7,'2026-02-14 20:01:52.103065','一晚上几个小时做出来一个具备登陆 增删改查的App，Ai 越来越强了！','https://images.unsplash.com/photo-1523275335684-37898b6baf30?','我的天啦',9999,'',NULL,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` (`username`, `avatar_url`, `display_name`) VALUES ('admin',NULL,'David');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `email`, `phone`, `created_at`, `updated_at`, `password`, `role`, `username`) VALUES (9,NULL,NULL,'2026-03-13 14:55:05','2026-03-13 14:55:05','$2a$10$6gLbZapr4TP3PPM3WI90Q.eQV9NLe07dx8WRjKqPcSr6mjj8lzCmW','ADMIN','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-14  0:49:56
