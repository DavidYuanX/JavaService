-- MySQL dump 10.13  Distrib 9.6.0, for macos15 (arm64)
--
-- Host: localhost    Database: crud_db
-- ------------------------------------------------------
-- Server version	9.6.0

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
-- SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
-- SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

-- SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '381325b4-1127-11f1-9e97-8cebf30129c9:1-99';

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` (`id`, `price`, `product_id`, `product_image`, `product_name`, `quantity`, `order_id`) VALUES (1,399,7,'https://placehold.co/400x400/e94560/eee?text=Scarf','羊绒围巾',2,1),(2,12999,1,'https://placehold.co/400x400/1a1a2e/eee?text=MacBook','MacBook Pro 14寸',1,2),(3,199,9,'https://placehold.co/400x400/2c3e50/eee?text=Jeans','牛仔休闲裤',1,3),(4,199,9,'https://placehold.co/400x400/2c3e50/eee?text=Jeans','牛仔休闲裤',1,4),(5,199,9,'https://placehold.co/400x400/2c3e50/eee?text=Jeans','牛仔休闲裤',1,5),(6,89,12,'https://placehold.co/400x400/3d1c02/eee?text=Choco','进口黑巧克力',1,5),(7,168,16,'https://placehold.co/400x400/8395a7/eee?text=Pillow','记忆棉枕头',1,6),(8,12999,1,'https://placehold.co/400x400/1a1a2e/eee?text=MacBook','MacBook Pro 14寸',1,7),(9,4799,4,'https://placehold.co/400x400/16213e/eee?text=iPad','iPad Air M2',1,8);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `created_at`, `status`, `total_amount`, `updated_at`, `username`) VALUES (1,'2026-03-12 01:59:26.314840','待发货',798,'2026-03-12 01:59:28.902333','admin'),(2,'2026-03-12 02:15:32.453562','待付款',12999,'2026-03-12 02:15:32.453562','admin'),(3,'2026-03-12 06:36:26.175441','待付款',199,'2026-03-12 06:36:26.175441','admin'),(4,'2026-03-12 06:36:34.597843','待付款',199,'2026-03-12 06:36:34.597843','admin'),(5,'2026-03-12 06:36:57.954681','待付款',288,'2026-03-12 06:36:57.954681','admin'),(6,'2026-03-12 10:09:45.379508','待发货',168,'2026-03-12 10:09:49.264310','admin'),(7,'2026-03-13 06:44:55.610150','待付款',12999,'2026-03-13 06:44:55.610150','admin'),(8,'2026-03-13 06:45:00.088285','已取消',4799,'2026-03-13 06:45:04.300025','admin');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `category`, `description`, `image_url`, `name`, `original_price`, `price`, `rating`, `sales_count`) VALUES (1,'数码','Apple M3 Pro 芯片，18GB 统一内存，512GB 固态硬盘，Liquid Retina XDR 显示屏，深空黑色。','https://placehold.co/400x400/1a1a2e/eee?text=MacBook','MacBook Pro 14寸',14999,12999,4.9,5230),(2,'数码','主动降噪，自适应音频，个性化空间音频，MagSafe 充电盒，USB-C 接口。','https://placehold.co/400x400/16213e/eee?text=AirPods','AirPods Pro 2',1999,1799,4.8,12450),(3,'数码','A18 Pro 芯片，钛金属设计，4800万像素摄像头系统，动作按钮，USB-C 接口，256GB。','https://placehold.co/400x400/2d3436/eee?text=iPhone','iPhone 16 Pro',9999,8999,4.9,28900),(4,'数码','M2 芯片，10.9英寸 Liquid Retina 显示屏，支持 Apple Pencil 和妙控键盘。','https://placehold.co/400x400/16213e/eee?text=iPad','iPad Air M2',5499,4799,4.8,7800),(5,'数码','49mm 钛金属表壳，精准双频GPS，水深仪，深度计，100m防水，续航36小时。','https://placehold.co/400x400/636e72/eee?text=Watch','Apple Watch Ultra 2',6499,5999,4.7,3200),(6,'服饰','100%纯棉面料，舒适透气，简约百搭圆领短袖，多色可选。适合春夏日常穿搭。','https://placehold.co/400x400/0f3460/eee?text=T-Shirt','纯棉休闲T恤',159,89,4.5,32100),(7,'服饰','100%山羊绒，柔软亲肤，经典纯色设计，冬季保暖必备，男女通用。','https://placehold.co/400x400/e94560/eee?text=Scarf','羊绒围巾',699,399,4.7,5400),(8,'服饰','免烫工艺，修身剪裁，进口面料，商务休闲两相宜，白色/蓝色/灰色可选。','https://placehold.co/400x400/34495e/eee?text=Shirt','轻奢商务衬衫',499,259,4.6,8700),(9,'服饰','弹力面料，直筒版型，经典水洗蓝，四季百搭，舒适不紧绷。','https://placehold.co/400x400/2c3e50/eee?text=Jeans','牛仔休闲裤',359,199,4.4,15300),(10,'食品','精选6种坚果，每日坚果混合装，独立小包装，新鲜锁味，送礼佳品。30包/盒。','https://placehold.co/400x400/e94560/eee?text=Nuts','有机坚果礼盒',198,128,4.7,15600),(11,'食品','明前特级龙井，手工采摘，清香甘甜，精美礼盒包装，250g装。产自杭州西湖。','https://placehold.co/400x400/16213e/eee?text=Tea','龙井春茶礼盒',398,268,4.8,9100),(12,'食品','85%可可含量，比利时原装进口，低糖配方，丝滑口感，100g*3盒装。','https://placehold.co/400x400/3d1c02/eee?text=Choco','进口黑巧克力',129,89,4.6,21000),(13,'食品','6种风味精酿，IPA/Stout/Lager/Wheat 各一瓶，330ml*6 礼盒装。','https://placehold.co/400x400/d4a017/eee?text=Beer','精酿啤酒组合装',158,99,4.5,7800),(14,'家居','三档调光，护眼LED光源，木质底座，适合书桌卧室床头使用。暖白光/冷白光可选。','https://placehold.co/400x400/1a1a2e/eee?text=Lamp','北欧简约台灯',239,159,4.4,6700),(15,'家居','LED温度显示，316不锈钢内胆，12小时长效保温，触控感应，500ml容量。','https://placehold.co/400x400/1a1a2e/eee?text=Cup','智能保温杯',199,129,4.5,18200),(16,'家居','慢回弹太空记忆棉，人体工学曲线设计，透气可拆洗外套，缓解颈椎疲劳。','https://placehold.co/400x400/8395a7/eee?text=Pillow','记忆棉枕头',299,168,4.7,12400),(17,'家居','天然大豆蜡，4种香型（白茶/薰衣草/柑橘/檀木），每只50g，燃烧时间约15h。','https://placehold.co/400x400/c0392b/eee?text=Candle','香薰蜡烛礼盒',139,79,4.3,9600),(18,'运动','轻便透气飞织鞋面，软弹缓震中底，防滑耐磨橡胶大底，适合日常通勤与运动。','https://placehold.co/400x400/533483/eee?text=Shoes','运动休闲鞋',499,299,4.6,8900),(19,'运动','NBR高密度材质，15mm加厚，双面防滑纹理，附带绑带和背包，适合家庭健身。','https://placehold.co/400x400/0f3460/eee?text=Yoga','瑜伽垫加厚防滑',129,79,4.3,21000),(20,'运动','钢丝绳芯，铝合金手柄，内置电子计数器，可调节长度，适合有氧训练和拳击热身。','https://placehold.co/400x400/27ae60/eee?text=Rope','跳绳计数器',89,49,4.4,16500),(21,'运动','包胶环保材质，5kg*2只，防滑手柄，家用健身增肌必备。','https://placehold.co/400x400/2c3e50/eee?text=Dumbbell','哑铃套装',249,159,4.5,5600),(22,'图书','包含《算法导论》《代码整洁之道》《设计模式》三本经典著作，程序员必读。','https://placehold.co/400x400/533483/eee?text=Books','编程入门经典套装',258,168,4.9,4300),(23,'图书','尤瓦尔·赫拉利代表作，《人类简史》《未来简史》《今日简史》精装合集。','https://placehold.co/400x400/8e44ad/eee?text=History','人类简史三部曲',199,128,4.8,11200),(24,'图书','圣埃克苏佩里经典之作，全新译本，精美水彩插图，中英双语对照，精装珍藏版。','https://placehold.co/400x400/3498db/eee?text=Prince','小王子（精装插图版）',68,39,4.9,35000),(25,'数码','RGB 背光，青轴，全键无冲','https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?w=400','极光机械键盘',NULL,2999,NULL,NULL),(26,'数码','主动降噪 30h 续航','https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400','无线降噪耳机',NULL,599,NULL,NULL),(27,'数码','20000mAh 双向快充','https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?w=400','便携充电宝',NULL,1299,NULL,NULL),(28,'数码','心率血氧 50米防水','https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400','智能手表',NULL,899,NULL,NULL),(29,'数码','人体工学 静音按键','https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400','无线鼠标',NULL,89,NULL,NULL),(30,'数码','确实是好东西','https://cdn.bigmodel.cn/static/platform/images/home/V2/tool_section_content6.png','好东西',NULL,888,NULL,NULL),(31,'数码','一晚上几个小时做出来一个具备登陆 增删改查的App，Ai 越来越强了！','https://images.unsplash.com/photo-1523275335684-37898b6baf30?','我的天啦',NULL,9999,NULL,NULL);
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
INSERT INTO `users` (`id`, `email`, `phone`, `password`, `role`, `username`) VALUES (5,NULL,'18681506225','$2a$10$sJ8ZL8O2OmmCLALZT6Svn.ct9vtPO3F73Xc63JnpksnFR7Saoefe2','ADMIN','admin'),(6,NULL,'13800000006','$2a$10$Q03TeJ4rern1wZSPNbshuOONnqemAwK1EGpnCo/vvXbdyJXFRXb8y','USER','David');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
-- SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-14 10:10:42
